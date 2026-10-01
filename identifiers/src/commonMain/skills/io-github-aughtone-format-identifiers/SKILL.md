---
name: io-github-aughtone-format-identifiers
description: >-
  The exact-notation member of the aughtone-format family, spelling an identifier
  you already hold the way a place expects. Mask a credit card number to its last
  four digits (•••• 1234) or group it for entry, Amex 4-6-5 included; space an
  IBAN in groups of four; write a MAC address with colons, hyphens, Cisco dots or
  bare hex; wrap a UUID in braces, as a urn:uuid, upper-case or without hyphens;
  compress or expand an IPv6 address (RFC 5952) or bracket it for a URL; show an
  IP address as its reverse DNS name, or an IPv4 as an integer; show a CIDR subnet
  as a netmask, wildcard mask or first-to-last address range; decode a Punycode
  xn-- internationalized domain name (IDN) to Unicode; format an E.164 phone
  number in national, international or RFC 3966 tel URI form; prefix a social
  handle with @. Input must already be canonical; it does not parse, validate or
  clean up user input and defines no value types. For rounded human text such as
  "1.5 MB" or "3 days ago" use io.github.aughtone:format-readable.
license: Apache-2.0
metadata:
  version: "4.1.0"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-identifiers

## What it solves

You hold an identifier that is already valid and canonical, and you need it to *look* the way a particular place expects. A MAC written with colons, IEEE hyphens, or Cisco dots. A UUID in braces, as a `urn:uuid:`, or upper-cased the way Apple's Foundation emits it. An IBAN spaced into fours for a statement. A card number grouped for entry, or masked to its last four for a receipt. An IPv4 as its reverse-DNS name or as a bare 32-bit integer. An IPv6 compressed the RFC 5952 way, or expanded, or bracketed for a URL. A CIDR block shown as a netmask, a wildcard mask, or a first-to-last range. An internationalized domain's `xn--` labels decoded back to `café.example`. An E.164 phone number grouped the way its own country writes it.

This is the "I already have the value, I just need the right spelling" layer — the fiddly string work you would otherwise hand-roll and get subtly wrong (IPv6 longest-zero-run compression, Amex `4-6-5` grouping, Punycode's bias adaptation).

**Called from Kotlin only.** The library publishes Kotlin Multiplatform artifacts (common, JVM, Android, and iOS via Kotlin); there is no separate Swift or JavaScript API.

## How it is meant to be used

Every identifier has **one extension function on `String`, named for what it produces**, taking a **defaulted notation enum**. The default is the canonical or most common form, so the ordinary case needs no argument; pass the enum to pick another spelling.

```kotlin
import io.github.aughtone.identifiers.*

"00:00:5e:00:53:01".formatMac()                    // "00:00:5e:00:53:01"   (default: Colon)
"00:00:5e:00:53:01".formatMac(MacNotation.CiscoDotted)  // "0000.5e00.5301"

"123e4567-e89b-12d3-a456-426614174000".formatUuid(UuidNotation.Braces)
// "{123e4567-e89b-12d3-a456-426614174000}"

"GB82WEST12345698765432".formatIban()              // "GB82 WEST 1234 5698 7654 32"
"4111111111111111".formatCardNumber(CardNotation.Masked)  // "•••• 1111"
"192.0.2.0/24".formatIpNetwork(IpNetworkNotation.Range)   // "192.0.2.0 - 192.0.2.255"
"xn--caf-dma.example".formatDomain()               // "café.example"        (default: Unicode)
"+16502530000".formatPhone()                       // "+1 650-253-0000"     (default: International)
```

The full set: `formatMac`, `formatUuid` (+ `Uuid.format`), `formatHandle`, `formatIban`, `formatCardNumber`, `formatIpv4`, `formatIpv6`, `formatIpNetwork`, `formatDomain`, `formatPhone`. Each notation enum documents its own forms.

Where a Kotlin-native type exists, an overload hangs off it: `kotlin.uuid.Uuid.format(notation)` sits beside `String.formatUuid`. It is `@ExperimentalUuidApi`, like `Uuid` itself.

## Invariants and traps

**Canonical input only — these throw on anything else.** Every formatter expects the identifier already normalized (the lower-case colon MAC, the 8-4-4-4-12 UUID, the compact upper-case IBAN, bare card digits, dotted-decimal IPv4, E.164 phone). Symptom: an `IllegalArgumentException` at the format call — non-canonical input is treated as a *programming error*, it is not "cleaned up". Do not point these at raw user input; normalize upstream first, then format. The one input latitude is UUID and IPv6 hex case, accepted in any case and emitted canonically.

**`CardNotation.Masked` is lossy by design.** It keeps only the last four digits behind a fixed `••••` group and does not preserve length. Symptom: you cannot recover the PAN from the output — use it for display only, never as a value you store or parse back.

**`formatDomain` decodes, it does not validate.** It is a table-free RFC 3492 Punycode decode of `xn--` labels and nothing more — no IDNA/UTS-46 validation or mapping, no confusable/mixed-script check (those need the Unicode tables and stay upstream). Symptom: it throws only when an `xn--` label is not valid Punycode; a structurally-odd-but-decodable label passes through unchecked.

**`formatIpv6` rejects IPv4-embedded forms.** Symptom: an `IllegalArgumentException` on a trailing dotted-quad such as `::ffff:192.0.2.1`; more than one `::`, the wrong group count, or a group over four hex digits also throw.

**`formatPhone` is the one impure formatter.** National and international grouping are not derivable from E.164 alone, so it uses per-country metadata from `io.github.aughtone:phonenumber` (the module's only dependency; the metadata is dead-code-eliminated for callers that never format a phone). Symptom: a country whose metadata is not bundled comes back as the national significant number without grouping, rather than throwing.

## What moved, and what it used to be called

**This module is new in 4.0.0 — there is no earlier version of it.** The trap is not a rename but a wrong instinct: an agent that already "knows" `aughtone-format` will not expect identifier formatting to exist here and will reach for `io.github.aughtone:format-readable`, or invent an identifier value type, or write the string surgery by hand.

- Identifier *notation* formatting lives in this module, published as the **separate** coordinate `io.github.aughtone:format-identifiers` (not folded into another module), as of 4.0.0.
- The functions are named `formatX`, **never** `formatReadableX`. There is no "readable" variant of an identifier formatter — see *What it is not for*.
- This module defines **no value types**. If you are looking for a `MacAddress`, `Iban`, or similar type, it is not here (see below).

## What it is not for

- **Not a parser, validator, or normalizer.** Canonical value in, chosen notation out. Cleaning up messy, spaced, mis-cased, or partial input is a normalization concern and belongs upstream, not here.
- **Not a home for value types.** This module only formats strings (and `Uuid`); it does not define identifier types.
- **Not human-magic readability.** Ordinals, `"1.5 MB"`, `"3 days ago"`, spelled-out numbers — that is `io.github.aughtone:format-readable`. This module is exact, reversible notation, not prose.
- **Domain:** not IDNA/UTS-46 validation, script/bidi checks, or confusable detection.
- **Card:** no Luhn check and no network detection beyond the Amex length/IIN grouping; `Masked` is display-only.
