# Chinese and Korean calendars not taken from ICU

ADR-0007 · 2026-10-24 · Status: accepted
Keywords: Chinese New Year wrong date, lunar month starts a day late, ICU Chinese calendar
          inaccurate, Intl chinese calendar disagrees, Android calendar lunar date wrong, Chinese
          New Year 2027 February 6 or 7, Hong Kong Observatory conversion tables, Korean lunar
          calendar Dangi, new moon near midnight, why the library disagrees with the platform

## Context

[ADR-0006](ADR-0006-astronomy-and-calendar-modules.md) decided that the Chinese and Korean calendars would use a bundled table for 1900–2100, generated from ICU's data, with months computed from the astronomy module outside it. The table was meant to settle the rare years where a new moon falls within minutes of midnight, which a calculation can put on the wrong day.

When the computation was checked against the table year by year, they disagreed in 16 Chinese months and 4 Korean ones, and every disagreement was a new moon within 14 minutes of midnight where the calendar is reckoned. To find which was right, both were checked against the Hong Kong Observatory's published Gregorian–Lunar calendar conversion tables for 1901–2100, the official reference for the Chinese calendar.

ICU's month starts are wrong in nine modern months:

| Month | Official start | ICU | New moon, Beijing time |
|---|---|---|---|
| 1st month 1954 (New Year) | 1954-02-03 | 1954-02-04 | 23:55 on the 3rd |
| 2nd month 1955 | 1955-02-22 | 1955-02-23 | 23:54 on the 22nd |
| 12th month 1998 | 1999-01-17 | 1999-01-18 | 23:46 on the 17th |
| 7th month 2012 | 2012-08-17 | 2012-08-18 | 23:54 on the 17th |
| 10th month 2018 | 2018-11-08 | 2018-11-07 | 00:02 on the 8th |
| 1st month 2027 (New Year) | 2027-02-06 | 2027-02-07 | 23:56 on the 6th |
| 1st month 2030 (New Year) | 2030-02-03 | 2030-02-02 | 00:07 on the 3rd |
| 9th month 2057 | 2057-09-28 | 2057-09-29 | 23:59:57 on the 28th |
| 2nd month 2070 | 2070-03-12 | 2070-03-13 | 23:52 on the 12th |

ICU computes these months itself rather than reading a published table, and in each of them the new moon is close enough to midnight that it lands on the other day. We believe the cause is the precision of ICU's own new-moon calculation; the pattern — every error within minutes of midnight, in both directions — is what that would produce. ICU is what the JVM's ICU4J, Android's calendars and JavaScript's `Intl` use, so an application showing a platform's Chinese calendar beside this library's will see these months differ, and the platform will be the one that is wrong.

This library's own computation, from the astronomy module's new moons (within two minutes of NASA's tables) and solar terms, reckoned at Beijing's local mean time before 1929 and UTC+8 since as the official tables are, agrees with the published tables in every month from 1901 to 2100 but one: April 1906, where the new moon falls within the calculation's own accuracy of midnight.

## Decision

The Chinese calendar's table is generated from the Hong Kong Observatory's published conversion tables, for the Chinese years 1901 to 2099 (the year 2100 needs months the tables do not reach), and its months are computed outside that range. No ICU data is used.

The Korean (Dangi) calendar is computed in every year, reckoned at Seoul (UTC+8 before 1912, UTC+9 since). No published Korean table could be generated from, and ICU's Korean months disagree with the computation in four months, each a new moon within five minutes of midnight in Seoul (in 2017, 2051 twice, and 2097), where the computation follows the true new moon. ICU's Korean month starts are kept as a test oracle, with those four listed as known differences.

ICU's Chinese and Korean data were rejected as the source: they are wrong exactly where a table was meant to help. Computation alone for the Chinese calendar was rejected because the published tables settle the one month the computation cannot, at the cost of about 600 bytes.

## Consequences

- The Chinese calendar matches the official tables in every month from 1901 to 2099, including the New Years of 2027 and 2030, where ICU-based platforms are a day out.
- The library's Chinese and Korean dates can differ from what a phone or a browser shows. The library's skill says so, so a developer comparing them knows which to trust.
- The Korean calendar has no official table behind it. Its months follow the true new moon at Seoul, and a month whose new moon falls within about two minutes of midnight could be a day out.
- The table depends on the Hong Kong Observatory continuing to publish its tables; the generator, `tools/calendars/chinese-table.mjs`, records where they come from.
