# `:identifiers`

Display formatters for canonical identifiers — rendering an already-normalized value in an alternative notation for humans. Input is a known-good canonical value (the output a normalizer or `aughtone-phonenumber` produces, or any valid canonical string); this module does not parse, validate, or normalize messy input.

Published as `io.github.aughtone:format-identifiers`.

## API convention

Every formatter follows the same shape, consistent with the rest of `aughtone-format`:

- **An extension function on the canonical value**, named for the identifier it produces from a `String` — `String.formatMac(...)`, `String.formatUuid(...)`, `String.formatDomain(...)`. We name what is produced because `String.format(...)` cannot be overloaded per identifier type.
- **The notation is a defaulted enum parameter**, so the common form needs no argument: `"00:00:5e:00:53:01".formatMac()` yields the canonical colon form; pass a `MacNotation` to choose another.
- **Where a Kotlin-native type exists, an overload hangs off it** — e.g. `kotlin.uuid.Uuid.format(...)` alongside the `String` overload.
- **No new value types.** Identifier value types are `aughtone-types`' responsibility; this module does not define them.
- **These are notation formatters, not `:readable` "human-magic"** (ordinals, data sizes). They are named `format*`, never `formatReadable*`; anything genuinely "readable" belongs in `:readable`.
- **Malformed input is a programming error** (input is expected canonical): formatters throw `IllegalArgumentException`.

## Dependencies

The notation formatters are pure, table-free transforms with no external dependency. The one exception is the phone formatter, which depends on `aughtone-phonenumber` for per-country grouping metadata; that metadata is dead-code-eliminated for consumers who never format a phone.

## Formatters

| Identifier | Entry point | Notations |
| :--- | :--- | :--- |
| MAC address | `String.formatMac` | `Colon` (default), `Ieee`, `CiscoDotted`, `Bare`, `UppercaseColon` |
| UUID | `String.formatUuid`, `Uuid.format` | `Hyphenated` (default), `Braces`, `Urn`, `Uppercase`, `NoHyphens` |
| Username | `String.formatHandle` | `@handle` |
| IBAN | `String.formatIban` | `Grouped` (default), `Compact` |
| Card number (PAN) | `String.formatCardNumber` | `Grouped` (default, issuer pattern), `Masked` (lossy) |
| IPv4 address | `String.formatIpv4` | `Dotted` (default), `ReverseDns`, `Integer` |
| IPv6 address | `String.formatIpv6` | `Compressed` (default), `Expanded`, `UrlHost`, `ReverseNibbles` |
| IPv4 network | `String.formatIpNetwork` | `Cidr` (default), `Netmask`, `Wildcard`, `Range` |

More identifiers (domain, phone) are tracked under the parent epic.
