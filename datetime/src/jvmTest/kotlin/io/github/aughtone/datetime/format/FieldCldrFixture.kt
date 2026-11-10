package io.github.aughtone.datetime.format

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR 48.0 (through ICU 78.2) by
// tools/cldr/field-fixture.mjs — do not edit by hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * The shape of what CLDR writes for a set of fields, one case per line:
 * `locale|skeleton|shape`. In a shape a field is `<` its class letter, then `t` for a name,
 * `n` for a number, `N` for a zero-padded number or `Y` for a two-digit year, then `>`; the
 * text between fields is CLDR's, with every kind of space written as a plain one.
 */
internal val fieldCldrFixture: String = """
CA|d|<dn>
CA|E|<Et>
CA|Ed|<Et> <dn>
CA|EEEE|<Et>
CA|EEEEd|<Et> <dn>
CA|M|<Mn>
CA|Md|<MN>-<dN>
CA|ME|<Mn> <Et>
CA|MEd|<Et>, <MN>-<dN>
CA|MEEEE|<Mn> <Et>
CA|MEEEEd|<Et>, <MN>-<dN>
CA|MM|<MN>
CA|MMd|<MN>-<dn>
CA|MME|<Et> (month: <MN>)
CA|MMEd|<Et>, <MN>-<dN>
CA|MMEEEE|<MN> <Et>
CA|MMEEEEd|<Et>, <MN>-<dN>
CA|MMM|<Mt>
CA|MMMd|<Mt> <dn>
CA|MMME|<Mt> <Et>
CA|MMMEd|<Et>, <Mt> <dn>
CA|MMMEEEE|<Mt> <Et>
CA|MMMEEEEd|<Et>, <Mt> <dn>
CA|MMMM|<Mt>
CA|MMMMd|<Mt> <dn>
CA|MMMME|<Et> (month: <Mt>)
CA|MMMMEd|<Et>, <Mt> <dn>
CA|MMMMEEEE|<Mt> <Et>
CA|MMMMEEEEd|<Et>, <Mt> <dn>
CA|y|<yn>
CA|yd|<yn> (day: <dn>)
CA|yE|<yn> <Et>
CA|yEd|<Et> <dn> <yn>
CA|yEEEE|<yn> <Et>
CA|yEEEEd|<Et> <dn> <yn>
CA|yM|<yn>-<MN>
CA|yMd|<yn>-<MN>-<dN>
CA|yME|<yn>-<MN> <Et>
CA|yMEd|<Et>, <yn>-<MN>-<dN>
CA|yMEEEE|<yn>-<MN> <Et>
CA|yMEEEEd|<Et>, <yn>-<MN>-<dN>
CA|yMM|<yn>-<MN>
CA|yMMd|<yn>-<MN>-<dn>
CA|yMME|<yn>-<MN> <Et>
CA|yMMEd|<Et>, <yn>-<MN>-<dN>
CA|yMMEEEE|<yn>-<MN> <Et>
CA|yMMEEEEd|<Et>, <yn>-<MN>-<dN>
CA|yMMM|<Mt> <yn>
CA|yMMMd|<Mt> <dn>, <yn>
CA|yMMME|<Mt> <yn> <Et>
CA|yMMMEd|<Et>, <Mt> <dn>, <yn>
CA|yMMMEEEE|<Mt> <yn> <Et>
CA|yMMMEEEEd|<Et>, <Mt> <dn>, <yn>
CA|yMMMM|<Mt> <yn>
CA|yMMMMd|<Mt> <dn>, <yn>
CA|yMMMME|<Mt> <yn> <Et>
CA|yMMMMEd|<Et>, <Mt> <dn>, <yn>
CA|yMMMMEEEE|<Mt> <yn> <Et>
CA|yMMMMEEEEd|<Et>, <Mt> <dn>, <yn>
CA|Gy|<yn> <Gt>
CA|GyMMMd|<Mt> <dn>, <yn> <Gt>
CA|GyMMMMEEEEd|<Et>, <Mt> <dn>, <yn> <Gt>
CA|GyMd|<Mn>/<dn>/<yn> <Gt>
CA|s|<sn>
CA|sz|<sn> <zt>
CA|szzzz|<sn> <zt>
CA|m|<mn>
CA|mz|<mn> <zt>
CA|mzzzz|<mn> <zt>
CA|ms|<mN>:<sN>
CA|msz|<mN>:<sN> <zt>
CA|mszzzz|<mN>:<sN> <zt>
CA|h|<hn> <at>
CA|hz|<hn> <at> <zt>
CA|hzzzz|<hn> <at> <zt>
CA|hs|<hn> <at> (second: <sn>)
CA|hsz|<hn> <at> <zt> (second: <sn>)
CA|hszzzz|<hn> <at> <zt> (second: <sn>)
CA|hm|<hn>:<mN> <at>
CA|hmz|<hn>:<mN> <at> <zt>
CA|hmzzzz|<hn>:<mN> <at> <zt>
CA|hms|<hn>:<mN>:<sN> <at>
CA|hmsz|<hn>:<mN>:<sN> <at> <zt>
CA|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
CA|H|<hN>
CA|Hz|<hN>h <zt>
CA|Hzzzz|<hN>h <zt>
CA|Hs|<hN> (second: <sn>)
CA|Hsz|<hN>h <zt> (second: <sn>)
CA|Hszzzz|<hN>h <zt> (second: <sn>)
CA|Hm|<hN>:<mN>
CA|Hmz|<hN>:<mN> <zt>
CA|Hmzzzz|<hN>:<mN> <zt>
CA|Hms|<hN>:<mN>:<sN>
CA|Hmsz|<hN>:<mN>:<sN> <zt>
CA|Hmszzzz|<hN>:<mN>:<sN> <zt>
CA|yMMMdh|<Mt> <dn>, <yn>, <hn> <at>
CA|yMMMdhm|<Mt> <dn>, <yn>, <hn>:<mN> <at>
CA|yMMMdHm|<Mt> <dn>, <yn>, <hN>:<mN>
CA|yMMMdhmz|<Mt> <dn>, <yn>, <hn>:<mN> <at> <zt>
CA|yMMMdHms|<Mt> <dn>, <yn>, <hN>:<mN>:<sN>
CA|yMMMdHmzzzz|<Mt> <dn>, <yn>, <hN>:<mN> <zt>
CA|MMMEdh|<Et>, <Mt> <dn>, <hn> <at>
CA|MMMEdhm|<Et>, <Mt> <dn>, <hn>:<mN> <at>
CA|MMMEdHm|<Et>, <Mt> <dn>, <hN>:<mN>
CA|MMMEdhmz|<Et>, <Mt> <dn>, <hn>:<mN> <at> <zt>
CA|MMMEdHms|<Et>, <Mt> <dn>, <hN>:<mN>:<sN>
CA|MMMEdHmzzzz|<Et>, <Mt> <dn>, <hN>:<mN> <zt>
CA|yMdh|<yn>-<MN>-<dN>, <hn> <at>
CA|yMdhm|<yn>-<MN>-<dN>, <hn>:<mN> <at>
CA|yMdHm|<yn>-<MN>-<dN>, <hN>:<mN>
CA|yMdhmz|<yn>-<MN>-<dN>, <hn>:<mN> <at> <zt>
CA|yMdHms|<yn>-<MN>-<dN>, <hN>:<mN>:<sN>
CA|yMdHmzzzz|<yn>-<MN>-<dN>, <hN>:<mN> <zt>
CA|Eh|<Et> <hn> <at>
CA|Ehm|<Et> <hn>:<mN> <at>
CA|EHm|<Et> <hN>:<mN>
CA|Ehmz|<Et>, <hn>:<mN> <at> <zt>
CA|EHms|<Et> <hN>:<mN>:<sN>
CA|EHmzzzz|<Et>, <hN>:<mN> <zt>
CA|dh|<dn>, <hn> <at>
CA|dhm|<dn>, <hn>:<mN> <at>
CA|dHm|<dn>, <hN>:<mN>
CA|dhmz|<dn>, <hn>:<mN> <at> <zt>
CA|dHms|<dn>, <hN>:<mN>:<sN>
CA|dHmzzzz|<dn>, <hN>:<mN> <zt>
CA|MMMMdh|<Mt> <dn> at <hn> <at>
CA|MMMMdhm|<Mt> <dn> at <hn>:<mN> <at>
CA|MMMMdHm|<Mt> <dn> at <hN>:<mN>
CA|MMMMdhmz|<Mt> <dn> at <hn>:<mN> <at> <zt>
CA|MMMMdHms|<Mt> <dn> at <hN>:<mN>:<sN>
CA|MMMMdHmzzzz|<Mt> <dn> at <hN>:<mN> <zt>
CA|yMMMMEEEEdh|<Et>, <Mt> <dn>, <yn> at <hn> <at>
CA|yMMMMEEEEdhm|<Et>, <Mt> <dn>, <yn> at <hn>:<mN> <at>
CA|yMMMMEEEEdHm|<Et>, <Mt> <dn>, <yn> at <hN>:<mN>
CA|yMMMMEEEEdhmz|<Et>, <Mt> <dn>, <yn> at <hn>:<mN> <at> <zt>
CA|yMMMMEEEEdHms|<Et>, <Mt> <dn>, <yn> at <hN>:<mN>:<sN>
CA|yMMMMEEEEdHmzzzz|<Et>, <Mt> <dn>, <yn> at <hN>:<mN> <zt>
CA|EEEEdh|<Et> <dn>, <hn> <at>
CA|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
CA|EEEEdHm|<Et> <dn>, <hN>:<mN>
CA|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
CA|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
CA|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
af|d|<dn>
af|E|<Et>
af|Ed|<Et> <dn>
af|EEEE|<Et>
af|EEEEd|<Et> <dn>
af|M|<Mn>
af|Md|<dN>-<MN>
af|ME|<Mn> <Et>
af|MEd|<Et> <dn>/<Mn>
af|MEEEE|<Mn> <Et>
af|MEEEEd|<Et> <dn>/<Mn>
af|MM|<MN>
af|MMd|<dN>-<MN>
af|MME|<Et> (maand: <MN>)
af|MMEd|<Et> <dn>/<MN>
af|MMEEEE|<MN> <Et>
af|MMEEEEd|<Et> <dn>/<MN>
af|MMM|<Mt>
af|MMMd|<dn> <Mt>
af|MMME|<Mt> <Et>
af|MMMEd|<Et> <dn> <Mt>
af|MMMEEEE|<Mt> <Et>
af|MMMEEEEd|<Et> <dn> <Mt>
af|MMMM|<Mt>
af|MMMMd|<dn> <Mt>
af|MMMME|<Et> (maand: <Mt>)
af|MMMMEd|<Et> <dn> <Mt>
af|MMMMEEEE|<Mt> <Et>
af|MMMMEEEEd|<Et> <dn> <Mt>
af|y|<yn>
af|yd|<yn> (dag: <dn>)
af|yE|<yn> <Et>
af|yEd|<yn> <Et> <dn>
af|yEEEE|<yn> <Et>
af|yEEEEd|<yn> <Et> <dn>
af|yM|<MN>-<yn>
af|yMd|<yn>-<MN>-<dN>
af|yME|<MN>-<yn> <Et>
af|yMEd|<Et> <yn>-<MN>-<dN>
af|yMEEEE|<MN>-<yn> <Et>
af|yMEEEEd|<Et> <yn>-<MN>-<dN>
af|yMM|<MN>-<yn>
af|yMMd|<yn>-<MN>-<dn>
af|yMME|<MN>-<yn> <Et>
af|yMMEd|<Et> <yn>-<MN>-<dN>
af|yMMEEEE|<MN>-<yn> <Et>
af|yMMEEEEd|<Et> <yn>-<MN>-<dN>
af|yMMM|<Mt> <yn>
af|yMMMd|<dn> <Mt> <yn>
af|yMMME|<Mt> <yn> <Et>
af|yMMMEd|<Et> <dn> <Mt> <yn>
af|yMMMEEEE|<Mt> <yn> <Et>
af|yMMMEEEEd|<Et> <dn> <Mt> <yn>
af|yMMMM|<Mt> <yn>
af|yMMMMd|<dn> <Mt> <yn>
af|yMMMME|<Mt> <yn> <Et>
af|yMMMMEd|<Et> <dn> <Mt> <yn>
af|yMMMMEEEE|<Mt> <yn> <Et>
af|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
af|Gy|<yn> <Gt>
af|GyMMMd|<dN> <Mt> <yn> <Gt>
af|GyMMMMEEEEd|<Et> <dN> <Mt> <yn> <Gt>
af|GyMd|<Gt> <yn>-<MN>-<dN>
af|s|<sn>
af|sz|<sn> <zt>
af|szzzz|<sn> <zt>
af|m|<mn>
af|mz|<mn> <zt>
af|mzzzz|<mn> <zt>
af|ms|<mN>:<sN>
af|msz|<mN>:<sN> <zt>
af|mszzzz|<mN>:<sN> <zt>
af|h|<hn> <at>
af|hz|<hn> <at> <zt>
af|hzzzz|<hn> <at> <zt>
af|hs|<hn> <at> (sekonde: <sn>)
af|hsz|<hn> <at> <zt> (sekonde: <sn>)
af|hszzzz|<hn> <at> <zt> (sekonde: <sn>)
af|hm|<hn>:<mN> <at>
af|hmz|<hn>:<mN> <at> <zt>
af|hmzzzz|<hn>:<mN> <at> <zt>
af|hms|<hn>:<mN>:<sN> <at>
af|hmsz|<hn>:<mN>:<sN> <at> <zt>
af|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
af|H|<hN>
af|Hz|<hN>h <zt>
af|Hzzzz|<hN>h <zt>
af|Hs|<hN> (sekonde: <sn>)
af|Hsz|<hN>h <zt> (sekonde: <sn>)
af|Hszzzz|<hN>h <zt> (sekonde: <sn>)
af|Hm|<hN>:<mN>
af|Hmz|<hN>:<mN> <zt>
af|Hmzzzz|<hN>:<mN> <zt>
af|Hms|<hN>:<mN>:<sN>
af|Hmsz|<hn>:<mN>:<sN> <zt>
af|Hmszzzz|<hn>:<mN>:<sN> <zt>
af|yMMMdh|<dn> <Mt> <yn> <hn> <at>
af|yMMMdhm|<dn> <Mt> <yn> <hn>:<mN> <at>
af|yMMMdHm|<dn> <Mt> <yn> <hN>:<mN>
af|yMMMdhmz|<dn> <Mt> <yn> <hn>:<mN> <at> <zt>
af|yMMMdHms|<dn> <Mt> <yn> <hN>:<mN>:<sN>
af|yMMMdHmzzzz|<dn> <Mt> <yn> <hN>:<mN> <zt>
af|MMMEdh|<Et> <dn> <Mt> <hn> <at>
af|MMMEdhm|<Et> <dn> <Mt> <hn>:<mN> <at>
af|MMMEdHm|<Et> <dn> <Mt> <hN>:<mN>
af|MMMEdhmz|<Et> <dn> <Mt> <hn>:<mN> <at> <zt>
af|MMMEdHms|<Et> <dn> <Mt> <hN>:<mN>:<sN>
af|MMMEdHmzzzz|<Et> <dn> <Mt> <hN>:<mN> <zt>
af|yMdh|<yn>-<MN>-<dN> <hn> <at>
af|yMdhm|<yn>-<MN>-<dN> <hn>:<mN> <at>
af|yMdHm|<yn>-<MN>-<dN> <hN>:<mN>
af|yMdhmz|<yn>-<MN>-<dN> <hn>:<mN> <at> <zt>
af|yMdHms|<yn>-<MN>-<dN> <hN>:<mN>:<sN>
af|yMdHmzzzz|<yn>-<MN>-<dN> <hN>:<mN> <zt>
af|Eh|<Et> <hn> <at>
af|Ehm|<Et> <hN>:<mN> <at>
af|EHm|<Et> <hN>:<mN>
af|Ehmz|<Et> <hn>:<mN> <at> <zt>
af|EHms|<Et> <hN>:<mN>:<sN>
af|EHmzzzz|<Et> <hN>:<mN> <zt>
af|dh|<dn> <hn> <at>
af|dhm|<dn> <hn>:<mN> <at>
af|dHm|<dn> <hN>:<mN>
af|dhmz|<dn> <hn>:<mN> <at> <zt>
af|dHms|<dn> <hN>:<mN>:<sN>
af|dHmzzzz|<dn> <hN>:<mN> <zt>
af|MMMMdh|<dn> <Mt> om <hn> <at>
af|MMMMdhm|<dn> <Mt> om <hn>:<mN> <at>
af|MMMMdHm|<dn> <Mt> om <hN>:<mN>
af|MMMMdhmz|<dn> <Mt> om <hn>:<mN> <at> <zt>
af|MMMMdHms|<dn> <Mt> om <hN>:<mN>:<sN>
af|MMMMdHmzzzz|<dn> <Mt> om <hN>:<mN> <zt>
af|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> om <hn> <at>
af|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> om <hn>:<mN> <at>
af|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> om <hN>:<mN>
af|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> om <hn>:<mN> <at> <zt>
af|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> om <hN>:<mN>:<sN>
af|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> om <hN>:<mN> <zt>
af|EEEEdh|<Et> <dn> <hn> <at>
af|EEEEdhm|<Et> <dn> <hn>:<mN> <at>
af|EEEEdHm|<Et> <dn> <hN>:<mN>
af|EEEEdhmz|<Et> <dn> <hn>:<mN> <at> <zt>
af|EEEEdHms|<Et> <dn> <hN>:<mN>:<sN>
af|EEEEdHmzzzz|<Et> <dn> <hN>:<mN> <zt>
ar|d|<dn>
ar|E|<Et>
ar|Ed|<Et>، <dn>
ar|EEEE|<Et>
ar|EEEEd|<Et>، <dn>
ar|M|<Mn>
ar|Md|<dn>/<Mn>
ar|ME|<Mn> <Et>
ar|MEd|<Et>، <dn>/<Mn>
ar|MEEEE|<Mn> <Et>
ar|MEEEEd|<Et>، <dn>/<Mn>
ar|MM|<MN>
ar|MMd|<dn>/<MN>
ar|MME|<Et> (الشهر: <MN>)
ar|MMEd|<Et>، <dn>/<MN>
ar|MMEEEE|<MN> <Et>
ar|MMEEEEd|<Et>، <dn>/<MN>
ar|MMM|<Mt>
ar|MMMd|<dn> <Mt>
ar|MMME|<Mt> <Et>
ar|MMMEd|<Et>، <dn> <Mt>
ar|MMMEEEE|<Mt> <Et>
ar|MMMEEEEd|<Et>، <dn> <Mt>
ar|MMMM|<Mt>
ar|MMMMd|<dn> <Mt>
ar|MMMME|<Et> (الشهر: <Mt>)
ar|MMMMEd|<Et>، <dn> <Mt>
ar|MMMMEEEE|<Mt> <Et>
ar|MMMMEEEEd|<Et>، <dn> <Mt>
ar|y|<yn>
ar|yd|<yn> (يوم: <dn>)
ar|yE|<yn> <Et>
ar|yEd|<yn> <Et>، <dn>
ar|yEEEE|<yn> <Et>
ar|yEEEEd|<yn> <Et>، <dn>
ar|yM|<Mn>/<yn>
ar|yMd|<dn>/<Mn>/<yn>
ar|yME|<Mn>/<yn> <Et>
ar|yMEd|<Et>، <dn>/<Mn>/<yn>
ar|yMEEEE|<Mn>/<yn> <Et>
ar|yMEEEEd|<Et>، <dn>/<Mn>/<yn>
ar|yMM|<MN>/<yn>
ar|yMMd|<dn>/<MN>/<yn>
ar|yMME|<MN>/<yn> <Et>
ar|yMMEd|<Et>، <dn>/<MN>/<yn>
ar|yMMEEEE|<MN>/<yn> <Et>
ar|yMMEEEEd|<Et>، <dn>/<MN>/<yn>
ar|yMMM|<Mt> <yn>
ar|yMMMd|<dn> <Mt> <yn>
ar|yMMME|<Mt> <yn> <Et>
ar|yMMMEd|<Et>، <dn> <Mt> <yn>
ar|yMMMEEEE|<Mt> <yn> <Et>
ar|yMMMEEEEd|<Et>، <dn> <Mt> <yn>
ar|yMMMM|<Mt> <yn>
ar|yMMMMd|<dn> <Mt> <yn>
ar|yMMMME|<Mt> <yn> <Et>
ar|yMMMMEd|<Et>، <dn> <Mt> <yn>
ar|yMMMMEEEE|<Mt> <yn> <Et>
ar|yMMMMEEEEd|<Et>، <dn> <Mt> <yn>
ar|Gy|<yn> <Gt>
ar|GyMMMd|<dn> <Mt> <yn> <Gt>
ar|GyMMMMEEEEd|<Et>، <dn> <Mt> <yn> <Gt>
ar|GyMd|<dN>-<MN>-<yn> <Gt>
ar|s|<sn>
ar|sz|<sn> <zt>
ar|szzzz|<sn> <zt>
ar|m|<mn>
ar|mz|<mn> <zt>
ar|mzzzz|<mn> <zt>
ar|ms|<mN>:<sN>
ar|msz|<mN>:<sN> <zt>
ar|mszzzz|<mN>:<sN> <zt>
ar|h|<hn> <at>
ar|hz|<hn> <at> <zt>
ar|hzzzz|<hn> <at> <zt>
ar|hs|<hn> <at> (الثواني: <sn>)
ar|hsz|<hn> <at> <zt> (الثواني: <sn>)
ar|hszzzz|<hn> <at> <zt> (الثواني: <sn>)
ar|hm|<hn>:<mN> <at>
ar|hmz|<hn>:<mN> <at> <zt>
ar|hmzzzz|<hn>:<mN> <at> <zt>
ar|hms|<hn>:<mN>:<sN> <at>
ar|hmsz|<hn>:<mN>:<sN> <at> <zt>
ar|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
ar|H|<hN>
ar|Hz|<hN>h <zt>
ar|Hzzzz|<hN>h <zt>
ar|Hs|<hN> (الثواني: <sn>)
ar|Hsz|<hN>h <zt> (الثواني: <sn>)
ar|Hszzzz|<hN>h <zt> (الثواني: <sn>)
ar|Hm|<hN>:<mN>
ar|Hmz|<hN>:<mN> <zt>
ar|Hmzzzz|<hN>:<mN> <zt>
ar|Hms|<hN>:<mN>:<sN>
ar|Hmsz|<hN>:<mN>:<sN> <zt>
ar|Hmszzzz|<hN>:<mN>:<sN> <zt>
ar|yMMMdh|<dn> <Mt> <yn>، <hn> <at>
ar|yMMMdhm|<dn> <Mt> <yn>، <hn>:<mN> <at>
ar|yMMMdHm|<dn> <Mt> <yn>، <hN>:<mN>
ar|yMMMdhmz|<dn> <Mt> <yn>، <hn>:<mN> <at> <zt>
ar|yMMMdHms|<dn> <Mt> <yn>، <hN>:<mN>:<sN>
ar|yMMMdHmzzzz|<dn> <Mt> <yn>، <hN>:<mN> <zt>
ar|MMMEdh|<Et>، <dn> <Mt>، <hn> <at>
ar|MMMEdhm|<Et>، <dn> <Mt>، <hn>:<mN> <at>
ar|MMMEdHm|<Et>، <dn> <Mt>، <hN>:<mN>
ar|MMMEdhmz|<Et>، <dn> <Mt>، <hn>:<mN> <at> <zt>
ar|MMMEdHms|<Et>، <dn> <Mt>، <hN>:<mN>:<sN>
ar|MMMEdHmzzzz|<Et>، <dn> <Mt>، <hN>:<mN> <zt>
ar|yMdh|<dn>/<Mn>/<yn>، <hn> <at>
ar|yMdhm|<dn>/<Mn>/<yn>، <hn>:<mN> <at>
ar|yMdHm|<dn>/<Mn>/<yn>، <hN>:<mN>
ar|yMdhmz|<dn>/<Mn>/<yn>، <hn>:<mN> <at> <zt>
ar|yMdHms|<dn>/<Mn>/<yn>، <hN>:<mN>:<sN>
ar|yMdHmzzzz|<dn>/<Mn>/<yn>، <hN>:<mN> <zt>
ar|Eh|<Et> <hn> <at>
ar|Ehm|<Et> <hn>:<mN> <at>
ar|EHm|<Et> <hN>:<mN>
ar|Ehmz|<Et>، <hn>:<mN> <at> <zt>
ar|EHms|<Et> <hN>:<mN>:<sN>
ar|EHmzzzz|<Et>، <hN>:<mN> <zt>
ar|dh|<dn>، <hn> <at>
ar|dhm|<dn>، <hn>:<mN> <at>
ar|dHm|<dn>، <hN>:<mN>
ar|dhmz|<dn>، <hn>:<mN> <at> <zt>
ar|dHms|<dn>، <hN>:<mN>:<sN>
ar|dHmzzzz|<dn>، <hN>:<mN> <zt>
ar|MMMMdh|<dn> <Mt> في <hn> <at>
ar|MMMMdhm|<dn> <Mt> في <hn>:<mN> <at>
ar|MMMMdHm|<dn> <Mt> في <hN>:<mN>
ar|MMMMdhmz|<dn> <Mt> في <hn>:<mN> <at> <zt>
ar|MMMMdHms|<dn> <Mt> في <hN>:<mN>:<sN>
ar|MMMMdHmzzzz|<dn> <Mt> في <hN>:<mN> <zt>
ar|yMMMMEEEEdh|<Et>، <dn> <Mt> <yn> في <hn> <at>
ar|yMMMMEEEEdhm|<Et>، <dn> <Mt> <yn> في <hn>:<mN> <at>
ar|yMMMMEEEEdHm|<Et>، <dn> <Mt> <yn> في <hN>:<mN>
ar|yMMMMEEEEdhmz|<Et>، <dn> <Mt> <yn> في <hn>:<mN> <at> <zt>
ar|yMMMMEEEEdHms|<Et>، <dn> <Mt> <yn> في <hN>:<mN>:<sN>
ar|yMMMMEEEEdHmzzzz|<Et>، <dn> <Mt> <yn> في <hN>:<mN> <zt>
ar|EEEEdh|<Et>، <dn>، <hn> <at>
ar|EEEEdhm|<Et>، <dn>، <hn>:<mN> <at>
ar|EEEEdHm|<Et>، <dn>، <hN>:<mN>
ar|EEEEdhmz|<Et>، <dn>، <hn>:<mN> <at> <zt>
ar|EEEEdHms|<Et>، <dn>، <hN>:<mN>:<sN>
ar|EEEEdHmzzzz|<Et>، <dn>، <hN>:<mN> <zt>
az|d|<dn>
az|E|<Et>
az|Ed|<dn> <Et>
az|EEEE|<Et>
az|EEEEd|<dn> <Et>
az|M|<Mn>
az|Md|<dN>.<MN>
az|ME|<Mn> <Et>
az|MEd|<dN>.<MN>, <Et>
az|MEEEE|<Mn> <Et>
az|MEEEEd|<dN>.<MN>, <Et>
az|MM|<MN>
az|MMd|<dN>.<MN>
az|MME|<Et> (Ay: <MN>)
az|MMEd|<dN>.<MN>, <Et>
az|MMEEEE|<MN> <Et>
az|MMEEEEd|<dN>.<MN>, <Et>
az|MMM|<Mt>
az|MMMd|<dn> <Mt>
az|MMME|<Mt> <Et>
az|MMMEd|<dn> <Mt>, <Et>
az|MMMEEEE|<Mt> <Et>
az|MMMEEEEd|<dn> <Mt>, <Et>
az|MMMM|<Mt>
az|MMMMd|<dn> <Mt>
az|MMMME|<Et> (Ay: <Mt>)
az|MMMMEd|<dn> <Mt>, <Et>
az|MMMMEEEE|<Mt> <Et>
az|MMMMEEEEd|<dn> <Mt>, <Et>
az|y|<yn>
az|yd|<yn> (Gün: <dn>)
az|yE|<yn> <Et>
az|yEd|<yn> <dn> <Et>
az|yEEEE|<yn> <Et>
az|yEEEEd|<yn> <dn> <Et>
az|yM|<MN>.<yn>
az|yMd|<dN>.<MN>.<yn>
az|yME|<MN>.<yn> <Et>
az|yMEd|<dN>.<MN>.<yn>, <Et>
az|yMEEEE|<MN>.<yn> <Et>
az|yMEEEEd|<dN>.<MN>.<yn>, <Et>
az|yMM|<MN>.<yn>
az|yMMd|<dN>.<MN>.<yn>
az|yMME|<MN>.<yn> <Et>
az|yMMEd|<dN>.<MN>.<yn>, <Et>
az|yMMEEEE|<MN>.<yn> <Et>
az|yMMEEEEd|<dN>.<MN>.<yn>, <Et>
az|yMMM|<Mt> <yn>
az|yMMMd|<dn> <Mt> <yn>
az|yMMME|<Mt> <yn> <Et>
az|yMMMEd|<dn> <Mt> <yn>, <Et>
az|yMMMEEEE|<Mt> <yn> <Et>
az|yMMMEEEEd|<dn> <Mt> <yn>, <Et>
az|yMMMM|<Mt> <yn>
az|yMMMMd|<dn> <Mt> <yn>
az|yMMMME|<Mt> <yn> <Et>
az|yMMMMEd|<dn> <Mt> <yn>, <Et>
az|yMMMMEEEE|<Mt> <yn> <Et>
az|yMMMMEEEEd|<dn> <Mt> <yn>, <Et>
az|Gy|<Gt> <yn>
az|GyMMMd|<Gt> <dn> <Mt> <yn>
az|GyMMMMEEEEd|<Gt> <dn> <Mt> <yn>, <Et>
az|GyMd|<Gt> <dn> <Mn> <yn>
az|s|<sn>
az|sz|<sn> <zt>
az|szzzz|<sn> <zt>
az|m|<mn>
az|mz|<mn> <zt>
az|mzzzz|<mn> <zt>
az|ms|<mN>:<sN>
az|msz|<mN>:<sN> <zt>
az|mszzzz|<mN>:<sN> <zt>
az|h|<hn> <at>
az|hz|<hn> <at> <zt>
az|hzzzz|<hn> <at> <zt>
az|hs|<hn> <at> (Saniyə: <sn>)
az|hsz|<hn> <at> <zt> (Saniyə: <sn>)
az|hszzzz|<hn> <at> <zt> (Saniyə: <sn>)
az|hm|<hn>:<mN> <at>
az|hmz|<hn>:<mN> <at> <zt>
az|hmzzzz|<hn>:<mN> <at> <zt>
az|hms|<hn>:<mN>:<sN> <at>
az|hmsz|<hn>:<mN>:<sN> <at> <zt>
az|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
az|H|<hN>
az|Hz|<hN>h <zt>
az|Hzzzz|<hN>h <zt>
az|Hs|<hN> (Saniyə: <sn>)
az|Hsz|<hN>h <zt> (Saniyə: <sn>)
az|Hszzzz|<hN>h <zt> (Saniyə: <sn>)
az|Hm|<hN>:<mN>
az|Hmz|<hN>:<mN> <zt>
az|Hmzzzz|<hN>:<mN> <zt>
az|Hms|<hN>:<mN>:<sN>
az|Hmsz|<hn>:<mN>:<sN> <zt>
az|Hmszzzz|<hn>:<mN>:<sN> <zt>
az|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
az|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
az|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
az|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
az|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
az|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
az|MMMEdh|<dn> <Mt>, <Et>, <hn> <at>
az|MMMEdhm|<dn> <Mt>, <Et>, <hn>:<mN> <at>
az|MMMEdHm|<dn> <Mt>, <Et>, <hN>:<mN>
az|MMMEdhmz|<dn> <Mt>, <Et>, <hn>:<mN> <at> <zt>
az|MMMEdHms|<dn> <Mt>, <Et>, <hN>:<mN>:<sN>
az|MMMEdHmzzzz|<dn> <Mt>, <Et>, <hN>:<mN> <zt>
az|yMdh|<dN>.<MN>.<yn>, <hn> <at>
az|yMdhm|<dN>.<MN>.<yn>, <hn>:<mN> <at>
az|yMdHm|<dN>.<MN>.<yn>, <hN>:<mN>
az|yMdhmz|<dN>.<MN>.<yn>, <hn>:<mN> <at> <zt>
az|yMdHms|<dN>.<MN>.<yn>, <hN>:<mN>:<sN>
az|yMdHmzzzz|<dN>.<MN>.<yn>, <hN>:<mN> <zt>
az|Eh|<Et> <hn> <at>
az|Ehm|<Et> <hn>:<mN> <at>
az|EHm|<Et> <hN>:<mN>
az|Ehmz|<Et>, <hn>:<mN> <at> <zt>
az|EHms|<Et> <hN>:<mN>:<sN>
az|EHmzzzz|<Et>, <hN>:<mN> <zt>
az|dh|<dn>, <hn> <at>
az|dhm|<dn>, <hn>:<mN> <at>
az|dHm|<dn>, <hN>:<mN>
az|dhmz|<dn>, <hn>:<mN> <at> <zt>
az|dHms|<dn>, <hN>:<mN>:<sN>
az|dHmzzzz|<dn>, <hN>:<mN> <zt>
az|MMMMdh|<dn> <Mt>/<hn> <at>
az|MMMMdhm|<dn> <Mt>/<hn>:<mN> <at>
az|MMMMdHm|<dn> <Mt>/<hN>:<mN>
az|MMMMdhmz|<dn> <Mt>/<hn>:<mN> <at> <zt>
az|MMMMdHms|<dn> <Mt>/<hN>:<mN>:<sN>
az|MMMMdHmzzzz|<dn> <Mt>/<hN>:<mN> <zt>
az|yMMMMEEEEdh|<dn> <Mt> <yn>, <Et>/<hn> <at>
az|yMMMMEEEEdhm|<dn> <Mt> <yn>, <Et>/<hn>:<mN> <at>
az|yMMMMEEEEdHm|<dn> <Mt> <yn>, <Et>/<hN>:<mN>
az|yMMMMEEEEdhmz|<dn> <Mt> <yn>, <Et>/<hn>:<mN> <at> <zt>
az|yMMMMEEEEdHms|<dn> <Mt> <yn>, <Et>/<hN>:<mN>:<sN>
az|yMMMMEEEEdHmzzzz|<dn> <Mt> <yn>, <Et>/<hN>:<mN> <zt>
az|EEEEdh|<dn> <Et>, <hn> <at>
az|EEEEdhm|<dn> <Et>, <hn>:<mN> <at>
az|EEEEdHm|<dn> <Et>, <hN>:<mN>
az|EEEEdhmz|<dn> <Et>, <hn>:<mN> <at> <zt>
az|EEEEdHms|<dn> <Et>, <hN>:<mN>:<sN>
az|EEEEdHmzzzz|<dn> <Et>, <hN>:<mN> <zt>
be|d|<dn>
be|E|<Et>
be|Ed|<dn>, <Et>
be|EEEE|<Et>
be|EEEEd|<dn>, <Et>
be|M|<Mn>
be|Md|<dn>.<Mn>
be|ME|<Mn> <Et>
be|MEd|<Et>, <dn>.<Mn>
be|MEEEE|<Mn> <Et>
be|MEEEEd|<Et>, <dn>.<Mn>
be|MM|<MN>
be|MMd|<dn>.<MN>
be|MME|<Et> (месяц: <MN>)
be|MMEd|<Et>, <dn>.<MN>
be|MMEEEE|<MN> <Et>
be|MMEEEEd|<Et>, <dn>.<MN>
be|MMM|<Mt>
be|MMMd|<dn> <Mt>
be|MMME|<Mt> <Et>
be|MMMEd|<Et>, <dn> <Mt>
be|MMMEEEE|<Mt> <Et>
be|MMMEEEEd|<Et>, <dn> <Mt>
be|MMMM|<Mt>
be|MMMMd|<dn> <Mt>
be|MMMME|<Et> (месяц: <Mt>)
be|MMMMEd|<Et>, <dn> <Mt>
be|MMMMEEEE|<Mt> <Et>
be|MMMMEEEEd|<Et>, <dn> <Mt>
be|y|<yn>
be|yd|<yn> (дзень: <dn>)
be|yE|<yn> <Et>
be|yEd|<yn> <dn>, <Et>
be|yEEEE|<yn> <Et>
be|yEEEEd|<yn> <dn>, <Et>
be|yM|<Mn>.<yn>
be|yMd|<dn>.<Mn>.<yn>
be|yME|<Mn>.<yn> <Et>
be|yMEd|<Et>, <dn>.<Mn>.<yn>
be|yMEEEE|<Mn>.<yn> <Et>
be|yMEEEEd|<Et>, <dn>.<Mn>.<yn>
be|yMM|<MN>.<yn>
be|yMMd|<dn>.<MN>.<yn>
be|yMME|<MN>.<yn> <Et>
be|yMMEd|<Et>, <dn>.<MN>.<yn>
be|yMMEEEE|<MN>.<yn> <Et>
be|yMMEEEEd|<Et>, <dn>.<MN>.<yn>
be|yMMM|<Mt> <yn>
be|yMMMd|<dn> <Mt> <yn>
be|yMMME|<Mt> <yn> <Et>
be|yMMMEd|<Et>, <dn> <Mt> <yn>
be|yMMMEEEE|<Mt> <yn> <Et>
be|yMMMEEEEd|<Et>, <dn> <Mt> <yn> г.
be|yMMMM|<Mt> <yn>
be|yMMMMd|<dn> <Mt> <yn> г.
be|yMMMME|<Mt> <yn> <Et>
be|yMMMMEd|<Et>, <dn> <Mt> <yn> г.
be|yMMMMEEEE|<Mt> <yn> <Et>
be|yMMMMEEEEd|<Et>, <dn> <Mt> <yn> г.
be|Gy|<yn> г. <Gt>
be|GyMMMd|<dn> <Mt> <yn> г. <Gt>
be|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> г. <Gt>
be|GyMd|<dN>.<MN>.<yn> <Gt>
be|s|<sn>
be|sz|<sn> <zt>
be|szzzz|<sn> <zt>
be|m|<mn>
be|mz|<mn> <zt>
be|mzzzz|<mn> <zt>
be|ms|<mN>.<sN>
be|msz|<mN>.<sN> <zt>
be|mszzzz|<mN>.<sN> <zt>
be|h|<hN> <at>
be|hz|<hn> <at> <zt>
be|hzzzz|<hn> <at> <zt>
be|hs|<hN> <at> (секунда: <sn>)
be|hsz|<hn> <at> <zt> (секунда: <sn>)
be|hszzzz|<hn> <at> <zt> (секунда: <sn>)
be|hm|<hn>:<mN> <at>
be|hmz|<hn>:<mN> <at> <zt>
be|hmzzzz|<hn>:<mN> <at> <zt>
be|hms|<hn>:<mN>:<sN> <at>
be|hmsz|<hn>:<mN>:<sN> <at> <zt>
be|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
be|H|<hn>
be|Hz|<hN> г <zt>
be|Hzzzz|<hN> г <zt>
be|Hs|<hn> (секунда: <sn>)
be|Hsz|<hN> г <zt> (секунда: <sn>)
be|Hszzzz|<hN> г <zt> (секунда: <sn>)
be|Hm|<hN>:<mN>
be|Hmz|<hN>:<mN> <zt>
be|Hmzzzz|<hN>:<mN> <zt>
be|Hms|<hN>:<mN>:<sN>
be|Hmsz|<hn>:<mN>:<sN> <zt>
be|Hmszzzz|<hn>:<mN>:<sN>, <zt>
be|yMMMdh|<dn> <Mt> <yn>, <hN> <at>
be|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
be|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
be|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
be|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
be|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
be|MMMEdh|<Et>, <dn> <Mt>, <hN> <at>
be|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
be|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
be|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
be|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
be|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
be|yMdh|<dn>.<Mn>.<yn>, <hN> <at>
be|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
be|yMdHm|<dn>.<Mn>.<yn>, <hN>:<mN>
be|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at> <zt>
be|yMdHms|<dn>.<Mn>.<yn>, <hN>:<mN>:<sN>
be|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>:<mN> <zt>
be|Eh|<Et> <hn> <at>
be|Ehm|<Et> <hn>:<mN> <at>
be|EHm|<Et> <hN>:<mN>
be|Ehmz|<Et>, <hn>:<mN> <at> <zt>
be|EHms|<Et> <hN>:<mN>:<sN>
be|EHmzzzz|<Et>, <hN>:<mN> <zt>
be|dh|<dn>, <hN> <at>
be|dhm|<dn>, <hn>:<mN> <at>
be|dHm|<dn>, <hN>:<mN>
be|dhmz|<dn>, <hn>:<mN> <at> <zt>
be|dHms|<dn>, <hN>:<mN>:<sN>
be|dHmzzzz|<dn>, <hN>:<mN> <zt>
be|MMMMdh|<dn> <Mt> у <hN> <at>
be|MMMMdhm|<dn> <Mt> у <hn>:<mN> <at>
be|MMMMdHm|<dn> <Mt> у <hN>:<mN>
be|MMMMdhmz|<dn> <Mt> у <hn>:<mN> <at> <zt>
be|MMMMdHms|<dn> <Mt> у <hN>:<mN>:<sN>
be|MMMMdHmzzzz|<dn> <Mt> у <hN>:<mN> <zt>
be|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> г. у <hN> <at>
be|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> г. у <hn>:<mN> <at>
be|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> г. у <hN>:<mN>
be|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> г. у <hn>:<mN> <at> <zt>
be|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> г. у <hN>:<mN>:<sN>
be|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> г. у <hN>:<mN> <zt>
be|EEEEdh|<dn>, <Et>, <hN> <at>
be|EEEEdhm|<dn>, <Et>, <hn>:<mN> <at>
be|EEEEdHm|<dn>, <Et>, <hN>:<mN>
be|EEEEdhmz|<dn>, <Et>, <hn>:<mN> <at> <zt>
be|EEEEdHms|<dn>, <Et>, <hN>:<mN>:<sN>
be|EEEEdHmzzzz|<dn>, <Et>, <hN>:<mN> <zt>
bg|d|<dn>
bg|E|<Et>
bg|Ed|<Et>, <dn>
bg|EEEE|<Et>
bg|EEEEd|<Et>, <dn>
bg|M|<Mn>
bg|Md|<dn>.<MN>
bg|ME|<Mn> <Et>
bg|MEd|<Et>, <dn>.<MN>
bg|MEEEE|<Mn> <Et>
bg|MEEEEd|<Et>, <dn>.<MN>
bg|MM|<MN>
bg|MMd|<dn>.<MN>
bg|MME|<Et> (месец: <MN>)
bg|MMEd|<Et>, <dn>.<MN>
bg|MMEEEE|<MN> <Et>
bg|MMEEEEd|<Et>, <dn>.<MN>
bg|MMM|<MN>
bg|MMMd|<dn>.<MN>
bg|MMME|<MN> <Et>
bg|MMMEd|<Et>, <dn>.<MN>
bg|MMMEEEE|<MN> <Et>
bg|MMMEEEEd|<Et>, <dn>.<MN>
bg|MMMM|<Mt>
bg|MMMMd|<dn> <Mt>
bg|MMMME|<Mt> <Et>
bg|MMMMEd|<Et>, <dn> <Mt>
bg|MMMMEEEE|<Mt> <Et>
bg|MMMMEEEEd|<Et>, <dn> <Mt>
bg|y|<yn> г.
bg|yd|<yn> г. (ден: <dn>)
bg|yE|<yn> г. <Et>
bg|yEd|<yn> г. <Et>, <dn>
bg|yEEEE|<yn> г. <Et>
bg|yEEEEd|<yn> г. <Et>, <dn>
bg|yM|<MN>.<yn> г.
bg|yMd|<dn>.<MN>.<yn> г.
bg|yME|<MN>.<yn> г. <Et>
bg|yMEd|<Et>, <dn>.<MN>.<yn> г.
bg|yMEEEE|<MN>.<yn> г. <Et>
bg|yMEEEEd|<Et>, <dn>.<MN>.<yn> г.
bg|yMM|<MN>.<yn> г.
bg|yMMd|<dn>.<MN>.<yn> г.
bg|yMME|<MN>.<yn> г. <Et>
bg|yMMEd|<Et>, <dn>.<MN>.<yn> г.
bg|yMMEEEE|<MN>.<yn> г. <Et>
bg|yMMEEEEd|<Et>, <dn>.<MN>.<yn> г.
bg|yMMM|<MN>.<yn> г.
bg|yMMMd|<dn>.<MN>.<yn> г.
bg|yMMME|<MN>.<yn> г. <Et>
bg|yMMMEd|<Et>, <dn>.<MN>.<yn> г.
bg|yMMMEEEE|<MN>.<yn> г. <Et>
bg|yMMMEEEEd|<Et>, <dn> <Mt> <yn> г.
bg|yMMMM|<Mt> <yn> г.
bg|yMMMMd|<dn> <Mt> <yn> г.
bg|yMMMME|<Mt> <yn> г. <Et>
bg|yMMMMEd|<Et>, <dn> <Mt> <yn> г.
bg|yMMMMEEEE|<Mt> <yn> г. <Et>
bg|yMMMMEEEEd|<Et>, <dn> <Mt> <yn> г.
bg|Gy|<yn> г. <Gt>
bg|GyMMMd|<dn>.<MN>.<yn> г. <Gt>
bg|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> г. <Gt>
bg|GyMd|<dN>.<MN>.<yn> г. <Gt>
bg|s|<sn>
bg|sz|<sn> <zt>
bg|szzzz|<sn> <zt>
bg|m|<mn>
bg|mz|<mn> <zt>
bg|mzzzz|<mn> <zt>
bg|ms|<mn>:<sN>
bg|msz|<mn>:<sN> <zt>
bg|mszzzz|<mn>:<sN> <zt>
bg|h|<hn> ч. <at>
bg|hz|<hn> ч. <at> <zt>
bg|hzzzz|<hn> ч. <at> <zt>
bg|hs|<hn> ч. <at> (секунда: <sn>)
bg|hsz|<hn> ч. <at> <zt> (секунда: <sn>)
bg|hszzzz|<hn> ч. <at> <zt> (секунда: <sn>)
bg|hm|<hn>:<mN> ч. <at>
bg|hmz|<hn>:<mN> ч. <at> <zt>
bg|hmzzzz|<hn>:<mN> ч. <at> <zt>
bg|hms|<hn>:<mN>:<sN> ч. <at>
bg|hmsz|<hn>:<mN>:<sN> ч. <at> <zt>
bg|hmszzzz|<hn>:<mN>:<sN> ч. <at> <zt>
bg|H|<hN> ч.
bg|Hz|<hN> ч <zt>
bg|Hzzzz|<hN> ч <zt>
bg|Hs|<hN> ч. (секунда: <sn>)
bg|Hsz|<hN> ч <zt> (секунда: <sn>)
bg|Hszzzz|<hN> ч <zt> (секунда: <sn>)
bg|Hm|<hN>:<mN> ч.
bg|Hmz|<hN>:<mN> ч. <zt>
bg|Hmzzzz|<hN>:<mN> ч. <zt>
bg|Hms|<hN>:<mN>:<sN> ч.
bg|Hmsz|<hn>:<mN>:<sN> ч. <zt>
bg|Hmszzzz|<hn>:<mN>:<sN> ч. <zt>
bg|yMMMdh|<dn>.<MN>.<yn> г., <hn> ч. <at>
bg|yMMMdhm|<dn>.<MN>.<yn> г., <hn>:<mN> ч. <at>
bg|yMMMdHm|<dn>.<MN>.<yn> г., <hN>:<mN> ч.
bg|yMMMdhmz|<dn>.<MN>.<yn> г., <hn>:<mN> ч. <at> <zt>
bg|yMMMdHms|<dn>.<MN>.<yn> г., <hN>:<mN>:<sN> ч.
bg|yMMMdHmzzzz|<dn>.<MN>.<yn> г., <hN>:<mN> ч. <zt>
bg|MMMEdh|<Et>, <dn>.<MN>, <hn> ч. <at>
bg|MMMEdhm|<Et>, <dn>.<MN>, <hn>:<mN> ч. <at>
bg|MMMEdHm|<Et>, <dn>.<MN>, <hN>:<mN> ч.
bg|MMMEdhmz|<Et>, <dn>.<MN>, <hn>:<mN> ч. <at> <zt>
bg|MMMEdHms|<Et>, <dn>.<MN>, <hN>:<mN>:<sN> ч.
bg|MMMEdHmzzzz|<Et>, <dn>.<MN>, <hN>:<mN> ч. <zt>
bg|yMdh|<dn>.<MN>.<yn> г., <hn> ч. <at>
bg|yMdhm|<dn>.<MN>.<yn> г., <hn>:<mN> ч. <at>
bg|yMdHm|<dn>.<MN>.<yn> г., <hN>:<mN> ч.
bg|yMdhmz|<dn>.<MN>.<yn> г., <hn>:<mN> ч. <at> <zt>
bg|yMdHms|<dn>.<MN>.<yn> г., <hN>:<mN>:<sN> ч.
bg|yMdHmzzzz|<dn>.<MN>.<yn> г., <hN>:<mN> ч. <zt>
bg|Eh|<Et>, <hn> <at>
bg|Ehm|<Et>, <hn>:<mN> ч. <at>
bg|EHm|<Et>, <hN>:<mN> ч.
bg|Ehmz|<Et>, <hn>:<mN> ч. <at> <zt>
bg|EHms|<Et>, <hN>:<mN>:<sN> ч.
bg|EHmzzzz|<Et>, <hN>:<mN> ч. <zt>
bg|dh|<dn>, <hn> ч. <at>
bg|dhm|<dn>, <hn>:<mN> ч. <at>
bg|dHm|<dn>, <hN>:<mN> ч.
bg|dhmz|<dn>, <hn>:<mN> ч. <at> <zt>
bg|dHms|<dn>, <hN>:<mN>:<sN> ч.
bg|dHmzzzz|<dn>, <hN>:<mN> ч. <zt>
bg|MMMMdh|<dn> <Mt> в <hn> ч. <at>
bg|MMMMdhm|<dn> <Mt> в <hn>:<mN> ч. <at>
bg|MMMMdHm|<dn> <Mt> в <hN>:<mN> ч.
bg|MMMMdhmz|<dn> <Mt> в <hn>:<mN> ч. <at> <zt>
bg|MMMMdHms|<dn> <Mt> в <hN>:<mN>:<sN> ч.
bg|MMMMdHmzzzz|<dn> <Mt> в <hN>:<mN> ч. <zt>
bg|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> г. в <hn> ч. <at>
bg|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> г. в <hn>:<mN> ч. <at>
bg|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> г. в <hN>:<mN> ч.
bg|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> г. в <hn>:<mN> ч. <at> <zt>
bg|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> г. в <hN>:<mN>:<sN> ч.
bg|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> г. в <hN>:<mN> ч. <zt>
bg|EEEEdh|<Et>, <dn>, <hn> ч. <at>
bg|EEEEdhm|<Et>, <dn>, <hn>:<mN> ч. <at>
bg|EEEEdHm|<Et>, <dn>, <hN>:<mN> ч.
bg|EEEEdhmz|<Et>, <dn>, <hn>:<mN> ч. <at> <zt>
bg|EEEEdHms|<Et>, <dn>, <hN>:<mN>:<sN> ч.
bg|EEEEdHmzzzz|<Et>, <dn>, <hN>:<mN> ч. <zt>
ca|d|<dn>
ca|E|<Et>
ca|Ed|<Et> <dn>
ca|EEEE|<Et>
ca|EEEEd|<Et> <dn>
ca|M|<Mn>
ca|Md|<dn>/<Mn>
ca|ME|<Mn> <Et>
ca|MEd|<Et> <dn>/<Mn>
ca|MEEEE|<Mn> <Et>
ca|MEEEEd|<Et> <dn>/<Mn>
ca|MM|<MN>
ca|MMd|<dn>/<MN>
ca|MME|<Et> (mes: <MN>)
ca|MMEd|<Et> <dn>/<MN>
ca|MMEEEE|<MN> <Et>
ca|MMEEEEd|<Et> <dn>/<MN>
ca|MMM|<Mt>
ca|MMMd|<dn> <Mt>
ca|MMME|<Mt> <Et>
ca|MMMEd|<Et>, <dn> <Mt>
ca|MMMEEEE|<Mt> <Et>
ca|MMMEEEEd|<Et>, <dn> <Mt>
ca|MMMM|<Mt>
ca|MMMMd|<dn> <Mt>
ca|MMMME|<Et> (mes: <Mt>)
ca|MMMMEd|<Et>, <dn> <Mt>
ca|MMMMEEEE|<Mt> <Et>
ca|MMMMEEEEd|<Et>, <dn> <Mt>
ca|y|<yn>
ca|yd|<yn> (dia: <dn>)
ca|yE|<yn> <Et>
ca|yEd|<yn> <Et> <dn>
ca|yEEEE|<yn> <Et>
ca|yEEEEd|<yn> <Et> <dn>
ca|yM|<Mn>/<yn>
ca|yMd|<dn>/<Mn>/<yn>
ca|yME|<Mn>/<yn> <Et>
ca|yMEd|<Et>, <dn>/<Mn>/<yn>
ca|yMEEEE|<Mn>/<yn> <Et>
ca|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
ca|yMM|<MN>/<yn>
ca|yMMd|<dn>/<MN>/<yn>
ca|yMME|<MN>/<yn> <Et>
ca|yMMEd|<Et>, <dn>/<MN>/<yn>
ca|yMMEEEE|<MN>/<yn> <Et>
ca|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
ca|yMMM|<Mt> del <yn>
ca|yMMMd|<dn> <Mt> del <yn>
ca|yMMME|<Mt> del <yn> <Et>
ca|yMMMEd|<Et>, <dn> <Mt> <yn>
ca|yMMMEEEE|<Mt> del <yn> <Et>
ca|yMMMEEEEd|<Et>, <dn> <Mt> del <yn>
ca|yMMMM|<Mt> del <yn>
ca|yMMMMd|<dn> <Mt> del <yn>
ca|yMMMME|<Mt> del <yn> <Et>
ca|yMMMMEd|<Et>, <dn> <Mt> del <yn>
ca|yMMMMEEEE|<Mt> del <yn> <Et>
ca|yMMMMEEEEd|<Et>, <dn> <Mt> del <yn>
ca|Gy|<yn> <Gt>
ca|GyMMMd|<dn> <Mt> del <yn> <Gt>
ca|GyMMMMEEEEd|<Et>, <dn> <Mt> del <yn> <Gt>
ca|GyMd|<dN>-<MN>-<yn> <Gt>
ca|s|<sn>
ca|sz|<sn> <zt>
ca|szzzz|<sn> <zt>
ca|m|<mn>
ca|mz|<mn> <zt>
ca|mzzzz|<mn> <zt>
ca|ms|<mN>:<sN>
ca|msz|<mN>:<sN> <zt>
ca|mszzzz|<mN>:<sN> <zt>
ca|h|<hn> <at>
ca|hz|<hn> <at> <zt>
ca|hzzzz|<hn> <at> <zt>
ca|hs|<hn> <at> (segon: <sn>)
ca|hsz|<hn> <at> <zt> (segon: <sn>)
ca|hszzzz|<hn> <at> <zt> (segon: <sn>)
ca|hm|<hn>:<mN> <at>
ca|hmz|<hn>:<mN> <at> <zt>
ca|hmzzzz|<hn>:<mN> <at> <zt>
ca|hms|<hn>:<mN>:<sN> <at>
ca|hmsz|<hn>:<mN>:<sN> <at> <zt>
ca|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
ca|H|<hn>
ca|Hz|<hn> h <zt>
ca|Hzzzz|<hn> h <zt>
ca|Hs|<hn> (segon: <sn>)
ca|Hsz|<hn> h <zt> (segon: <sn>)
ca|Hszzzz|<hn> h <zt> (segon: <sn>)
ca|Hm|<hn>:<mN>
ca|Hmz|<hn>:<mN> <zt>
ca|Hmzzzz|<hn>:<mN> <zt>
ca|Hms|<hn>:<mN>:<sN>
ca|Hmsz|<hn>:<mN>:<sN> <zt>
ca|Hmszzzz|<hn>:<mN>:<sN> (<zt>)
ca|yMMMdh|<dn> <Mt> del <yn>, <hn> <at>
ca|yMMMdhm|<dn> <Mt> del <yn>, <hn>:<mN> <at>
ca|yMMMdHm|<dn> <Mt> del <yn>, <hn>:<mN>
ca|yMMMdhmz|<dn> <Mt> del <yn>, <hn>:<mN> <at> <zt>
ca|yMMMdHms|<dn> <Mt> del <yn>, <hn>:<mN>:<sN>
ca|yMMMdHmzzzz|<dn> <Mt> del <yn>, <hn>:<mN> <zt>
ca|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
ca|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
ca|MMMEdHm|<Et>, <dn> <Mt>, <hn>:<mN>
ca|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
ca|MMMEdHms|<Et>, <dn> <Mt>, <hn>:<mN>:<sN>
ca|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hn>:<mN> <zt>
ca|yMdh|<dn>/<Mn>/<yn> <hn> <at>
ca|yMdhm|<dn>/<Mn>/<yn> <hn>:<mN> <at>
ca|yMdHm|<dn>/<Mn>/<yn> <hn>:<mN>
ca|yMdhmz|<dn>/<Mn>/<yn> <hn>:<mN> <at> <zt>
ca|yMdHms|<dn>/<Mn>/<yn> <hn>:<mN>:<sN>
ca|yMdHmzzzz|<dn>/<Mn>/<yn> <hn>:<mN> <zt>
ca|Eh|<Et> <hn> <at>
ca|Ehm|<Et> <hn>:<mN> <at>
ca|EHm|<Et> <hn>:<mN>
ca|Ehmz|<Et> <hn>:<mN> <at> <zt>
ca|EHms|<Et> <hn>:<mN>:<sN>
ca|EHmzzzz|<Et> <hn>:<mN> <zt>
ca|dh|<dn> <hn> <at>
ca|dhm|<dn> <hn>:<mN> <at>
ca|dHm|<dn> <hn>:<mN>
ca|dhmz|<dn> <hn>:<mN> <at> <zt>
ca|dHms|<dn> <hn>:<mN>:<sN>
ca|dHmzzzz|<dn> <hn>:<mN> <zt>
ca|MMMMdh|<dn> <Mt>, a les <hn> <at>
ca|MMMMdhm|<dn> <Mt>, a les <hn>:<mN> <at>
ca|MMMMdHm|<dn> <Mt>, a les <hn>:<mN>
ca|MMMMdhmz|<dn> <Mt>, a les <hn>:<mN> <at> <zt>
ca|MMMMdHms|<dn> <Mt>, a les <hn>:<mN>:<sN>
ca|MMMMdHmzzzz|<dn> <Mt>, a les <hn>:<mN> <zt>
ca|yMMMMEEEEdh|<Et>, <dn> <Mt> del <yn>, a les <hn> <at>
ca|yMMMMEEEEdhm|<Et>, <dn> <Mt> del <yn>, a les <hn>:<mN> <at>
ca|yMMMMEEEEdHm|<Et>, <dn> <Mt> del <yn>, a les <hn>:<mN>
ca|yMMMMEEEEdhmz|<Et>, <dn> <Mt> del <yn>, a les <hn>:<mN> <at> <zt>
ca|yMMMMEEEEdHms|<Et>, <dn> <Mt> del <yn>, a les <hn>:<mN>:<sN>
ca|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> del <yn>, a les <hn>:<mN> <zt>
ca|EEEEdh|<Et> <dn> <hn> <at>
ca|EEEEdhm|<Et> <dn> <hn>:<mN> <at>
ca|EEEEdHm|<Et> <dn> <hn>:<mN>
ca|EEEEdhmz|<Et> <dn> <hn>:<mN> <at> <zt>
ca|EEEEdHms|<Et> <dn> <hn>:<mN>:<sN>
ca|EEEEdHmzzzz|<Et> <dn> <hn>:<mN> <zt>
cs|d|<dn>.
cs|E|<Et>
cs|Ed|<Et> <dn>.
cs|EEEE|<Et>
cs|EEEEd|<Et> <dn>.
cs|M|<Mn>
cs|Md|<dn>. <Mn>.
cs|ME|<Mn> <Et>
cs|MEd|<Et> <dn>. <Mn>.
cs|MEEEE|<Mn> <Et>
cs|MEEEEd|<Et> <dn>. <Mn>.
cs|MM|<MN>
cs|MMd|<dn>. <MN>.
cs|MME|<Et> (měsíc: <MN>)
cs|MMEd|<Et> <dn>. <MN>.
cs|MMEEEE|<MN> <Et>
cs|MMEEEEd|<Et> <dn>. <MN>.
cs|MMM|<Mt>
cs|MMMd|<dn>. <Mn>.
cs|MMME|<Mt> <Et>
cs|MMMEd|<Et> <dn>. <Mn>.
cs|MMMEEEE|<Mt> <Et>
cs|MMMEEEEd|<Et> <dn>. <Mn>.
cs|MMMM|<Mt>
cs|MMMMd|<dn>. <Mt>
cs|MMMME|<Et> (měsíc: <Mt>)
cs|MMMMEd|<Et> <dn>. <Mt>
cs|MMMMEEEE|<Mt> <Et>
cs|MMMMEEEEd|<Et> <dn>. <Mt>
cs|y|<yn>
cs|yd|<yn> (den: <dn>.)
cs|yE|<yn> <Et>
cs|yEd|<yn> <Et> <dn>.
cs|yEEEE|<yn> <Et>
cs|yEEEEd|<yn> <Et> <dn>.
cs|yM|<Mn>/<yn>
cs|yMd|<dn>. <Mn>. <yn>
cs|yME|<Mn>/<yn> <Et>
cs|yMEd|<Et> <dn>. <Mn>. <yn>
cs|yMEEEE|<Mn>/<yn> <Et>
cs|yMEEEEd|<Et> <dn>. <Mn>. <yn>
cs|yMM|<MN>/<yn>
cs|yMMd|<dn>. <MN>. <yn>
cs|yMME|<MN>/<yn> <Et>
cs|yMMEd|<Et> <dn>. <MN>. <yn>
cs|yMMEEEE|<MN>/<yn> <Et>
cs|yMMEEEEd|<Et> <dn>. <MN>. <yn>
cs|yMMM|<Mt> <yn>
cs|yMMMd|<dn>. <Mn>. <yn>
cs|yMMME|<Mt> <yn> <Et>
cs|yMMMEd|<Et> <dn>. <Mn>. <yn>
cs|yMMMEEEE|<Mt> <yn> <Et>
cs|yMMMEEEEd|<Et> <dn>. <Mt> <yn>
cs|yMMMM|<Mt> <yn>
cs|yMMMMd|<dn>. <Mt> <yn>
cs|yMMMME|<Mt> <yn> <Et>
cs|yMMMMEd|<Et> <dn>. <Mt> <yn>
cs|yMMMMEEEE|<Mt> <yn> <Et>
cs|yMMMMEEEEd|<Et> <dn>. <Mt> <yn>
cs|Gy|<yn> <Gt>
cs|GyMMMd|<dn>. <Mn>. <yn> <Gt>
cs|GyMMMMEEEEd|<Et> <dn>. <Mt> <yn> <Gt>
cs|GyMd|<dn>. <Mn>. <yn> <Gt>
cs|s|<sn>
cs|sz|<sn> <zt>
cs|szzzz|<sn> <zt>
cs|m|<mn>
cs|mz|<mn> <zt>
cs|mzzzz|<mn> <zt>
cs|ms|<mN>:<sN>
cs|msz|<mN>:<sN> <zt>
cs|mszzzz|<mN>:<sN> <zt>
cs|h|<hn> <at>
cs|hz|<hn> <at> <zt>
cs|hzzzz|<hn> <at> <zt>
cs|hs|<hn> <at> (sekunda: <sn>)
cs|hsz|<hn> <at> <zt> (sekunda: <sn>)
cs|hszzzz|<hn> <at> <zt> (sekunda: <sn>)
cs|hm|<hn>:<mN> <at>
cs|hmz|<hn>:<mN> <at> <zt>
cs|hmzzzz|<hn>:<mN> <at> <zt>
cs|hms|<hn>:<mN>:<sN> <at>
cs|hmsz|<hn>:<mN>:<sN> <at> <zt>
cs|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
cs|H|<hn>
cs|Hz|<hn> h <zt>
cs|Hzzzz|<hn> h <zt>
cs|Hs|<hn> (sekunda: <sn>)
cs|Hsz|<hn> h <zt> (sekunda: <sn>)
cs|Hszzzz|<hn> h <zt> (sekunda: <sn>)
cs|Hm|<hn>:<mN>
cs|Hmz|<hn>:<mN> <zt>
cs|Hmzzzz|<hn>:<mN> <zt>
cs|Hms|<hn>:<mN>:<sN>
cs|Hmsz|<hn>:<mN>:<sN> <zt>
cs|Hmszzzz|<hn>:<mN>:<sN>, <zt>
cs|yMMMdh|<dn>. <Mn>. <yn> <hn> <at>
cs|yMMMdhm|<dn>. <Mn>. <yn> <hn>:<mN> <at>
cs|yMMMdHm|<dn>. <Mn>. <yn> <hn>:<mN>
cs|yMMMdhmz|<dn>. <Mn>. <yn> <hn>:<mN> <at> <zt>
cs|yMMMdHms|<dn>. <Mn>. <yn> <hn>:<mN>:<sN>
cs|yMMMdHmzzzz|<dn>. <Mn>. <yn> <hn>:<mN> <zt>
cs|MMMEdh|<Et> <dn>. <Mn>. <hn> <at>
cs|MMMEdhm|<Et> <dn>. <Mn>. <hn>:<mN> <at>
cs|MMMEdHm|<Et> <dn>. <Mn>. <hn>:<mN>
cs|MMMEdhmz|<Et> <dn>. <Mn>. <hn>:<mN> <at> <zt>
cs|MMMEdHms|<Et> <dn>. <Mn>. <hn>:<mN>:<sN>
cs|MMMEdHmzzzz|<Et> <dn>. <Mn>. <hn>:<mN> <zt>
cs|yMdh|<dn>. <Mn>. <yn> <hn> <at>
cs|yMdhm|<dn>. <Mn>. <yn> <hn>:<mN> <at>
cs|yMdHm|<dn>. <Mn>. <yn> <hn>:<mN>
cs|yMdhmz|<dn>. <Mn>. <yn> <hn>:<mN> <at> <zt>
cs|yMdHms|<dn>. <Mn>. <yn> <hn>:<mN>:<sN>
cs|yMdHmzzzz|<dn>. <Mn>. <yn> <hn>:<mN> <zt>
cs|Eh|<Et> <hn> <at>
cs|Ehm|<Et> <hn>:<mN> <at>
cs|EHm|<Et> <hn>:<mN>
cs|Ehmz|<Et> <hn>:<mN> <at> <zt>
cs|EHms|<Et> <hn>:<mN>:<sN>
cs|EHmzzzz|<Et> <hn>:<mN> <zt>
cs|dh|<dn>. <hn> <at>
cs|dhm|<dn>. <hn>:<mN> <at>
cs|dHm|<dn>. <hn>:<mN>
cs|dhmz|<dn>. <hn>:<mN> <at> <zt>
cs|dHms|<dn>. <hn>:<mN>:<sN>
cs|dHmzzzz|<dn>. <hn>:<mN> <zt>
cs|MMMMdh|<dn>. <Mt> v <hn> <at>
cs|MMMMdhm|<dn>. <Mt> v <hn>:<mN> <at>
cs|MMMMdHm|<dn>. <Mt> v <hn>:<mN>
cs|MMMMdhmz|<dn>. <Mt> v <hn>:<mN> <at> <zt>
cs|MMMMdHms|<dn>. <Mt> v <hn>:<mN>:<sN>
cs|MMMMdHmzzzz|<dn>. <Mt> v <hn>:<mN> <zt>
cs|yMMMMEEEEdh|<Et> <dn>. <Mt> <yn> v <hn> <at>
cs|yMMMMEEEEdhm|<Et> <dn>. <Mt> <yn> v <hn>:<mN> <at>
cs|yMMMMEEEEdHm|<Et> <dn>. <Mt> <yn> v <hn>:<mN>
cs|yMMMMEEEEdhmz|<Et> <dn>. <Mt> <yn> v <hn>:<mN> <at> <zt>
cs|yMMMMEEEEdHms|<Et> <dn>. <Mt> <yn> v <hn>:<mN>:<sN>
cs|yMMMMEEEEdHmzzzz|<Et> <dn>. <Mt> <yn> v <hn>:<mN> <zt>
cs|EEEEdh|<Et> <dn>. <hn> <at>
cs|EEEEdhm|<Et> <dn>. <hn>:<mN> <at>
cs|EEEEdHm|<Et> <dn>. <hn>:<mN>
cs|EEEEdhmz|<Et> <dn>. <hn>:<mN> <at> <zt>
cs|EEEEdHms|<Et> <dn>. <hn>:<mN>:<sN>
cs|EEEEdHmzzzz|<Et> <dn>. <hn>:<mN> <zt>
da|d|<dn>.
da|E|<Et>
da|Ed|<Et> den <dn>.
da|EEEE|<Et>
da|EEEEd|<Et> den <dn>.
da|M|<Mn>
da|Md|<dn>.<Mn>
da|ME|<Mn> <Et>
da|MEd|<Et> <dn>.<Mn>
da|MEEEE|<Mn> <Et>
da|MEEEEd|<Et> <dn>.<Mn>
da|MM|<MN>
da|MMd|<dn>.<MN>
da|MME|<Et> (måned: <MN>)
da|MMEd|<Et> <dn>.<MN>
da|MMEEEE|<MN> <Et>
da|MMEEEEd|<Et> <dn>.<MN>
da|MMM|<Mt>
da|MMMd|<dn>. <Mt>
da|MMME|<Mt> <Et>
da|MMMEd|<Et> <dn>. <Mt>
da|MMMEEEE|<Mt> <Et>
da|MMMEEEEd|<Et> <dn>. <Mt>
da|MMMM|<Mt>
da|MMMMd|<dn>. <Mt>
da|MMMME|<Et> (måned: <Mt>)
da|MMMMEd|<Et> <dn>. <Mt>
da|MMMMEEEE|<Mt> <Et>
da|MMMMEEEEd|<Et> <dn>. <Mt>
da|y|<yn>
da|yd|<yn> (dag: <dn>.)
da|yE|<yn> <Et>
da|yEd|<yn> <Et> den <dn>.
da|yEEEE|<yn> <Et>
da|yEEEEd|<yn> <Et> den <dn>.
da|yM|<Mn>.<yn>
da|yMd|<dn>.<Mn>.<yn>
da|yME|<Mn>.<yn> <Et>
da|yMEd|<Et> <dn>.<Mn>.<yn>
da|yMEEEE|<Mn>.<yn> <Et>
da|yMEEEEd|<Et> <dn>.<Mn>.<yn>
da|yMM|<MN>.<yn>
da|yMMd|<dn>.<MN>.<yn>
da|yMME|<MN>.<yn> <Et>
da|yMMEd|<Et> <dn>.<MN>.<yn>
da|yMMEEEE|<MN>.<yn> <Et>
da|yMMEEEEd|<Et> <dn>.<MN>.<yn>
da|yMMM|<Mt> <yn>
da|yMMMd|<dn>. <Mt> <yn>
da|yMMME|<Mt> <yn> <Et>
da|yMMMEd|<Et> <dn>. <Mt> <yn>
da|yMMMEEEE|<Mt> <yn> <Et>
da|yMMMEEEEd|<Et> den <dn>. <Mt> <yn>
da|yMMMM|<Mt> <yn>
da|yMMMMd|<dn>. <Mt> <yn>
da|yMMMME|<Mt> <yn> <Et>
da|yMMMMEd|<Et> den <dn>. <Mt> <yn>
da|yMMMMEEEE|<Mt> <yn> <Et>
da|yMMMMEEEEd|<Et> den <dn>. <Mt> <yn>
da|Gy|<yn> <Gt>
da|GyMMMd|<dn>. <Mt> <yn> <Gt>
da|GyMMMMEEEEd|<Et> <dn>. <Mt> <yn> <Gt>
da|GyMd|<dn>.<Mn>.<yn> <Gt>
da|s|<sn>
da|sz|<sn> <zt>
da|szzzz|<sn> <zt>
da|m|<mn>
da|mz|<mn> <zt>
da|mzzzz|<mn> <zt>
da|ms|<mN>.<sN>
da|msz|<mN>.<sN> <zt>
da|mszzzz|<mN>.<sN> <zt>
da|h|<hn> <at>
da|hz|<hn> <at> <zt>
da|hzzzz|<hn> <at> <zt>
da|hs|<hn> <at> (sekund: <sn>)
da|hsz|<hn> <at> <zt> (sekund: <sn>)
da|hszzzz|<hn> <at> <zt> (sekund: <sn>)
da|hm|<hn>.<mN> <at>
da|hmz|<hn>.<mN> <at> <zt>
da|hmzzzz|<hn>.<mN> <at> <zt>
da|hms|<hn>.<mN>.<sN> <at>
da|hmsz|<hn>.<mN>.<sN> <at> <zt>
da|hmszzzz|<hn>.<mN>.<sN> <at> <zt>
da|H|<hN>
da|Hz|<hN>h <zt>
da|Hzzzz|<hN>h <zt>
da|Hs|<hN> (sekund: <sn>)
da|Hsz|<hN>h <zt> (sekund: <sn>)
da|Hszzzz|<hN>h <zt> (sekund: <sn>)
da|Hm|<hN>.<mN>
da|Hmz|<hN>.<mN> <zt>
da|Hmzzzz|<hN>.<mN> <zt>
da|Hms|<hN>.<mN>.<sN>
da|Hmsz|<hn>.<mN>.<sN> <zt>
da|Hmszzzz|<hn>.<mN>.<sN> <zt>
da|yMMMdh|<dn>. <Mt> <yn>, <hn> <at>
da|yMMMdhm|<dn>. <Mt> <yn>, <hn>.<mN> <at>
da|yMMMdHm|<dn>. <Mt> <yn>, <hN>.<mN>
da|yMMMdhmz|<dn>. <Mt> <yn>, <hn>.<mN> <at> <zt>
da|yMMMdHms|<dn>. <Mt> <yn>, <hN>.<mN>.<sN>
da|yMMMdHmzzzz|<dn>. <Mt> <yn>, <hN>.<mN> <zt>
da|MMMEdh|<Et> <dn>. <Mt>, <hn> <at>
da|MMMEdhm|<Et> <dn>. <Mt>, <hn>.<mN> <at>
da|MMMEdHm|<Et> <dn>. <Mt>, <hN>.<mN>
da|MMMEdhmz|<Et> <dn>. <Mt>, <hn>.<mN> <at> <zt>
da|MMMEdHms|<Et> <dn>. <Mt>, <hN>.<mN>.<sN>
da|MMMEdHmzzzz|<Et> <dn>. <Mt>, <hN>.<mN> <zt>
da|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
da|yMdhm|<dn>.<Mn>.<yn>, <hn>.<mN> <at>
da|yMdHm|<dn>.<Mn>.<yn>, <hN>.<mN>
da|yMdhmz|<dn>.<Mn>.<yn>, <hn>.<mN> <at> <zt>
da|yMdHms|<dn>.<Mn>.<yn>, <hN>.<mN>.<sN>
da|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>.<mN> <zt>
da|Eh|<Et> <hn> <at>
da|Ehm|<Et> <hn>.<mN> <at>
da|EHm|<Et> <hN>.<mN>
da|Ehmz|<Et>, <hn>.<mN> <at> <zt>
da|EHms|<Et> <hN>.<mN>.<sN>
da|EHmzzzz|<Et>, <hN>.<mN> <zt>
da|dh|<dn>., <hn> <at>
da|dhm|<dn>., <hn>.<mN> <at>
da|dHm|<dn>., <hN>.<mN>
da|dhmz|<dn>., <hn>.<mN> <at> <zt>
da|dHms|<dn>., <hN>.<mN>.<sN>
da|dHmzzzz|<dn>., <hN>.<mN> <zt>
da|MMMMdh|<dn>. <Mt> kl. <hn> <at>
da|MMMMdhm|<dn>. <Mt> kl. <hn>.<mN> <at>
da|MMMMdHm|<dn>. <Mt> kl. <hN>.<mN>
da|MMMMdhmz|<dn>. <Mt> kl. <hn>.<mN> <at> <zt>
da|MMMMdHms|<dn>. <Mt> kl. <hN>.<mN>.<sN>
da|MMMMdHmzzzz|<dn>. <Mt> kl. <hN>.<mN> <zt>
da|yMMMMEEEEdh|<Et> den <dn>. <Mt> <yn> kl. <hn> <at>
da|yMMMMEEEEdhm|<Et> den <dn>. <Mt> <yn> kl. <hn>.<mN> <at>
da|yMMMMEEEEdHm|<Et> den <dn>. <Mt> <yn> kl. <hN>.<mN>
da|yMMMMEEEEdhmz|<Et> den <dn>. <Mt> <yn> kl. <hn>.<mN> <at> <zt>
da|yMMMMEEEEdHms|<Et> den <dn>. <Mt> <yn> kl. <hN>.<mN>.<sN>
da|yMMMMEEEEdHmzzzz|<Et> den <dn>. <Mt> <yn> kl. <hN>.<mN> <zt>
da|EEEEdh|<Et> den <dn>., <hn> <at>
da|EEEEdhm|<Et> den <dn>., <hn>.<mN> <at>
da|EEEEdHm|<Et> den <dn>., <hN>.<mN>
da|EEEEdhmz|<Et> den <dn>., <hn>.<mN> <at> <zt>
da|EEEEdHms|<Et> den <dn>., <hN>.<mN>.<sN>
da|EEEEdHmzzzz|<Et> den <dn>., <hN>.<mN> <zt>
de|d|<dn>
de|E|<Et>
de|Ed|<Et>, <dn>.
de|EEEE|<Et>
de|EEEEd|<Et>, <dn>.
de|M|<Mn>
de|Md|<dn>.<Mn>.
de|ME|<Mn> <Et>
de|MEd|<Et>, <dn>.<Mn>.
de|MEEEE|<Mn> <Et>
de|MEEEEd|<Et>, <dn>.<Mn>.
de|MM|<MN>
de|MMd|<dN>.<MN>.
de|MME|<Et> (Monat: <MN>)
de|MMEd|<Et>, <dn>.<MN>.
de|MMEEEE|<MN> <Et>
de|MMEEEEd|<Et>, <dn>.<MN>.
de|MMM|<Mt>
de|MMMd|<dn>. <Mt>
de|MMME|<Mt> <Et>
de|MMMEd|<Et>, <dn>. <Mt>
de|MMMEEEE|<Mt> <Et>
de|MMMEEEEd|<Et>, <dn>. <Mt>
de|MMMM|<Mt>
de|MMMMd|<dn>. <Mt>
de|MMMME|<Et> (Monat: <Mt>)
de|MMMMEd|<Et>, <dn>. <Mt>
de|MMMMEEEE|<Mt> <Et>
de|MMMMEEEEd|<Et>, <dn>. <Mt>
de|y|<yn>
de|yd|<yn> (Tag: <dn>)
de|yE|<yn> <Et>
de|yEd|<yn> <Et>, <dn>.
de|yEEEE|<yn> <Et>
de|yEEEEd|<yn> <Et>, <dn>.
de|yM|<Mn>/<yn>
de|yMd|<dn>.<Mn>.<yn>
de|yME|<Mn>/<yn> <Et>
de|yMEd|<Et>, <dn>.<Mn>.<yn>
de|yMEEEE|<Mn>/<yn> <Et>
de|yMEEEEd|<Et>, <dn>.<Mn>.<yn>
de|yMM|<MN>/<yn>
de|yMMd|<dn>.<MN>.<yn>
de|yMME|<MN>/<yn> <Et>
de|yMMEd|<Et>, <dn>.<MN>.<yn>
de|yMMEEEE|<MN>/<yn> <Et>
de|yMMEEEEd|<Et>, <dn>.<MN>.<yn>
de|yMMM|<Mt> <yn>
de|yMMMd|<dn>. <Mt> <yn>
de|yMMME|<Mt> <yn> <Et>
de|yMMMEd|<Et>, <dn>. <Mt> <yn>
de|yMMMEEEE|<Mt> <yn> <Et>
de|yMMMEEEEd|<Et>, <dn>. <Mt> <yn>
de|yMMMM|<Mt> <yn>
de|yMMMMd|<dn>. <Mt> <yn>
de|yMMMME|<Mt> <yn> <Et>
de|yMMMMEd|<Et>, <dn>. <Mt> <yn>
de|yMMMMEEEE|<Mt> <yn> <Et>
de|yMMMMEEEEd|<Et>, <dn>. <Mt> <yn>
de|Gy|<yn> <Gt>
de|GyMMMd|<dn>. <Mt> <yn> <Gt>
de|GyMMMMEEEEd|<Et>, <dn>. <Mt> <yn> <Gt>
de|GyMd|<dN>.<MN>.<yn> <Gt>
de|s|<sn>
de|sz|<sn> <zt>
de|szzzz|<sn> <zt>
de|m|<mn>
de|mz|<mn> <zt>
de|mzzzz|<mn> <zt>
de|ms|<mN>:<sN>
de|msz|<mN>:<sN> <zt>
de|mszzzz|<mN>:<sN> <zt>
de|h|<hn> <at>
de|hz|<hn> <at> <zt>
de|hzzzz|<hn> <at> <zt>
de|hs|<hn> <at> (Sekunde: <sn>)
de|hsz|<hn> <at> <zt> (Sekunde: <sn>)
de|hszzzz|<hn> <at> <zt> (Sekunde: <sn>)
de|hm|<hn>:<mN> <at>
de|hmz|<hn>:<mN> <at> <zt>
de|hmzzzz|<hn>:<mN> <at> <zt>
de|hms|<hn>:<mN>:<sN> <at>
de|hmsz|<hn>:<mN>:<sN> <at> <zt>
de|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
de|H|<hN> Uhr
de|Hz|<hN> Uhr <zt>
de|Hzzzz|<hN> Uhr <zt>
de|Hs|<hN> Uhr (Sekunde: <sn>)
de|Hsz|<hN> Uhr <zt> (Sekunde: <sn>)
de|Hszzzz|<hN> Uhr <zt> (Sekunde: <sn>)
de|Hm|<hN>:<mN>
de|Hmz|<hN>:<mN> <zt>
de|Hmzzzz|<hN>:<mN> <zt>
de|Hms|<hN>:<mN>:<sN>
de|Hmsz|<hn>:<mN>:<sN> <zt>
de|Hmszzzz|<hn>:<mN>:<sN> <zt>
de|yMMMdh|<dn>. <Mt> <yn>, <hn> <at>
de|yMMMdhm|<dn>. <Mt> <yn>, <hn>:<mN> <at>
de|yMMMdHm|<dn>. <Mt> <yn>, <hN>:<mN>
de|yMMMdhmz|<dn>. <Mt> <yn>, <hn>:<mN> <at> <zt>
de|yMMMdHms|<dn>. <Mt> <yn>, <hN>:<mN>:<sN>
de|yMMMdHmzzzz|<dn>. <Mt> <yn>, <hN>:<mN> <zt>
de|MMMEdh|<Et>, <dn>. <Mt>, <hn> <at>
de|MMMEdhm|<Et>, <dn>. <Mt>, <hn>:<mN> <at>
de|MMMEdHm|<Et>, <dn>. <Mt>, <hN>:<mN>
de|MMMEdhmz|<Et>, <dn>. <Mt>, <hn>:<mN> <at> <zt>
de|MMMEdHms|<Et>, <dn>. <Mt>, <hN>:<mN>:<sN>
de|MMMEdHmzzzz|<Et>, <dn>. <Mt>, <hN>:<mN> <zt>
de|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
de|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
de|yMdHm|<dn>.<Mn>.<yn>, <hN>:<mN>
de|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at> <zt>
de|yMdHms|<dn>.<Mn>.<yn>, <hN>:<mN>:<sN>
de|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>:<mN> <zt>
de|Eh|<Et>, <hn> <at>
de|Ehm|<Et> <hn>:<mN> <at>
de|EHm|<Et>, <hN>:<mN>
de|Ehmz|<Et>, <hn>:<mN> <at> <zt>
de|EHms|<Et>, <hN>:<mN>:<sN>
de|EHmzzzz|<Et>, <hN>:<mN> <zt>
de|dh|<dn>, <hn> <at>
de|dhm|<dn>, <hn>:<mN> <at>
de|dHm|<dn>, <hN>:<mN>
de|dhmz|<dn>, <hn>:<mN> <at> <zt>
de|dHms|<dn>, <hN>:<mN>:<sN>
de|dHmzzzz|<dn>, <hN>:<mN> <zt>
de|MMMMdh|<dn>. <Mt> um <hn> <at>
de|MMMMdhm|<dn>. <Mt> um <hn>:<mN> <at>
de|MMMMdHm|<dn>. <Mt> um <hN>:<mN>
de|MMMMdhmz|<dn>. <Mt> um <hn>:<mN> <at> <zt>
de|MMMMdHms|<dn>. <Mt> um <hN>:<mN>:<sN>
de|MMMMdHmzzzz|<dn>. <Mt> um <hN>:<mN> <zt>
de|yMMMMEEEEdh|<Et>, <dn>. <Mt> <yn> um <hn> <at>
de|yMMMMEEEEdhm|<Et>, <dn>. <Mt> <yn> um <hn>:<mN> <at>
de|yMMMMEEEEdHm|<Et>, <dn>. <Mt> <yn> um <hN>:<mN>
de|yMMMMEEEEdhmz|<Et>, <dn>. <Mt> <yn> um <hn>:<mN> <at> <zt>
de|yMMMMEEEEdHms|<Et>, <dn>. <Mt> <yn> um <hN>:<mN>:<sN>
de|yMMMMEEEEdHmzzzz|<Et>, <dn>. <Mt> <yn> um <hN>:<mN> <zt>
de|EEEEdh|<Et>, <dn>., <hn> <at>
de|EEEEdhm|<Et>, <dn>., <hn>:<mN> <at>
de|EEEEdHm|<Et>, <dn>., <hN>:<mN>
de|EEEEdhmz|<Et>, <dn>., <hn>:<mN> <at> <zt>
de|EEEEdHms|<Et>, <dn>., <hN>:<mN>:<sN>
de|EEEEdHmzzzz|<Et>, <dn>., <hN>:<mN> <zt>
el|d|<dn>
el|E|<Et>
el|Ed|<Et> <dn>
el|EEEE|<Et>
el|EEEEd|<Et> <dn>
el|M|<Mn>
el|Md|<dn>/<Mn>
el|ME|<Mn> <Et>
el|MEd|<Et> <dn>/<Mn>
el|MEEEE|<Mn> <Et>
el|MEEEEd|<Et> <dn>/<Mn>
el|MM|<MN>
el|MMd|<dn>/<MN>
el|MME|<Et> (μήνας: <MN>)
el|MMEd|<Et> <dn>/<MN>
el|MMEEEE|<MN> <Et>
el|MMEEEEd|<Et> <dn>/<MN>
el|MMM|<Mt>
el|MMMd|<dn> <Mt>
el|MMME|<Mt> <Et>
el|MMMEd|<Et> <dn> <Mt>
el|MMMEEEE|<Mt> <Et>
el|MMMEEEEd|<Et> <dn> <Mt>
el|MMMM|<Mt>
el|MMMMd|<dn> <Mt>
el|MMMME|<Et> (μήνας: <Mt>)
el|MMMMEd|<Et> <dn> <Mt>
el|MMMMEEEE|<Mt> <Et>
el|MMMMEEEEd|<Et> <dn> <Mt>
el|y|<yn>
el|yd|<yn> (ημέρα: <dn>)
el|yE|<yn> <Et>
el|yEd|<yn> <Et> <dn>
el|yEEEE|<yn> <Et>
el|yEEEEd|<yn> <Et> <dn>
el|yM|<Mn>/<yn>
el|yMd|<dn>/<Mn>/<yn>
el|yME|<Mn>/<yn> <Et>
el|yMEd|<Et> <dn>/<Mn>/<yn>
el|yMEEEE|<Mn>/<yn> <Et>
el|yMEEEEd|<Et> <dn>/<Mn>/<yn>
el|yMM|<MN>/<yn>
el|yMMd|<dn>/<MN>/<yn>
el|yMME|<MN>/<yn> <Et>
el|yMMEd|<Et> <dn>/<MN>/<yn>
el|yMMEEEE|<MN>/<yn> <Et>
el|yMMEEEEd|<Et> <dn>/<MN>/<yn>
el|yMMM|<Mt> <yn>
el|yMMMd|<dn> <Mt> <yn>
el|yMMME|<Mt> <yn> <Et>
el|yMMMEd|<Et> <dn> <Mt> <yn>
el|yMMMEEEE|<Mt> <yn> <Et>
el|yMMMEEEEd|<Et> <dn> <Mt> <yn>
el|yMMMM|<Mt> <yn>
el|yMMMMd|<dn> <Mt> <yn>
el|yMMMME|<Mt> <yn> <Et>
el|yMMMMEd|<Et> <dn> <Mt> <yn>
el|yMMMMEEEE|<Mt> <yn> <Et>
el|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
el|Gy|<yn> <Gt>
el|GyMMMd|<dn> <Mt> <yn> <Gt>
el|GyMMMMEEEEd|<Et> <dn> <Mt> <yn> <Gt>
el|GyMd|<dn>/<Mn>/<yn> <Gt>
el|s|<sn>
el|sz|<sn> <zt>
el|szzzz|<sn> <zt>
el|m|<mn>
el|mz|<mn> <zt>
el|mzzzz|<mn> <zt>
el|ms|<mN>:<sN>
el|msz|<mN>:<sN> <zt>
el|mszzzz|<mN>:<sN> <zt>
el|h|<hn> <at>
el|hz|<hn> <at> <zt>
el|hzzzz|<hn> <at> <zt>
el|hs|<hn> <at> (δευτερόλεπτο: <sn>)
el|hsz|<hn> <at> <zt> (δευτερόλεπτο: <sn>)
el|hszzzz|<hn> <at> <zt> (δευτερόλεπτο: <sn>)
el|hm|<hn>:<mN> <at>
el|hmz|<hn>:<mN> <at> <zt>
el|hmzzzz|<hn>:<mN> <at> <zt>
el|hms|<hn>:<mN>:<sN> <at>
el|hmsz|<hn>:<mN>:<sN> <at> <zt>
el|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
el|H|<hN>
el|Hz|<hN>h (<zt>)
el|Hzzzz|<hN>h (<zt>)
el|Hs|<hN> (δευτερόλεπτο: <sn>)
el|Hsz|<hN>h (<zt>) (δευτερόλεπτο: <sn>)
el|Hszzzz|<hN>h (<zt>) (δευτερόλεπτο: <sn>)
el|Hm|<hN>:<mN>
el|Hmz|<hN>:<mN> (<zt>)
el|Hmzzzz|<hN>:<mN> (<zt>)
el|Hms|<hN>:<mN>:<sN>
el|Hmsz|<hN>:<mN>:<sN> (<zt>)
el|Hmszzzz|<hN>:<mN>:<sN> (<zt>)
el|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
el|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
el|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
el|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
el|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
el|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> (<zt>)
el|MMMEdh|<Et> <dn> <Mt>, <hn> <at>
el|MMMEdhm|<Et> <dn> <Mt>, <hn>:<mN> <at>
el|MMMEdHm|<Et> <dn> <Mt>, <hN>:<mN>
el|MMMEdhmz|<Et> <dn> <Mt>, <hn>:<mN> <at> <zt>
el|MMMEdHms|<Et> <dn> <Mt>, <hN>:<mN>:<sN>
el|MMMEdHmzzzz|<Et> <dn> <Mt>, <hN>:<mN> (<zt>)
el|yMdh|<dn>/<Mn>/<yn>, <hn> <at>
el|yMdhm|<dn>/<Mn>/<yn>, <hn>:<mN> <at>
el|yMdHm|<dn>/<Mn>/<yn>, <hN>:<mN>
el|yMdhmz|<dn>/<Mn>/<yn>, <hn>:<mN> <at> <zt>
el|yMdHms|<dn>/<Mn>/<yn>, <hN>:<mN>:<sN>
el|yMdHmzzzz|<dn>/<Mn>/<yn>, <hN>:<mN> (<zt>)
el|Eh|<Et> <hn> <at>
el|Ehm|<Et> <hn>:<mN> <at>
el|EHm|<Et> <hN>:<mN>
el|Ehmz|<Et>, <hn>:<mN> <at> <zt>
el|EHms|<Et> <hN>:<mN>:<sN>
el|EHmzzzz|<Et>, <hN>:<mN> (<zt>)
el|dh|<dn>, <hn> <at>
el|dhm|<dn>, <hn>:<mN> <at>
el|dHm|<dn>, <hN>:<mN>
el|dhmz|<dn>, <hn>:<mN> <at> <zt>
el|dHms|<dn>, <hN>:<mN>:<sN>
el|dHmzzzz|<dn>, <hN>:<mN> (<zt>)
el|MMMMdh|<dn> <Mt> στις <hn> <at>
el|MMMMdhm|<dn> <Mt> στις <hn>:<mN> <at>
el|MMMMdHm|<dn> <Mt> στις <hN>:<mN>
el|MMMMdhmz|<dn> <Mt> στις <hn>:<mN> <at> <zt>
el|MMMMdHms|<dn> <Mt> στις <hN>:<mN>:<sN>
el|MMMMdHmzzzz|<dn> <Mt> στις <hN>:<mN> (<zt>)
el|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> στις <hn> <at>
el|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> στις <hn>:<mN> <at>
el|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> στις <hN>:<mN>
el|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> στις <hn>:<mN> <at> <zt>
el|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> στις <hN>:<mN>:<sN>
el|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> στις <hN>:<mN> (<zt>)
el|EEEEdh|<Et> <dn>, <hn> <at>
el|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
el|EEEEdHm|<Et> <dn>, <hN>:<mN>
el|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
el|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
el|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> (<zt>)
en|d|<dn>
en|E|<Et>
en|Ed|<dn> <Et>
en|EEEE|<Et>
en|EEEEd|<dn> <Et>
en|M|<Mn>
en|Md|<Mn>/<dn>
en|ME|<Mn> <Et>
en|MEd|<Et>, <Mn>/<dn>
en|MEEEE|<Mn> <Et>
en|MEEEEd|<Et>, <Mn>/<dn>
en|MM|<MN>
en|MMd|<MN>/<dn>
en|MME|<Et> (month: <MN>)
en|MMEd|<Et>, <MN>/<dn>
en|MMEEEE|<MN> <Et>
en|MMEEEEd|<Et>, <MN>/<dn>
en|MMM|<Mt>
en|MMMd|<Mt> <dn>
en|MMME|<Mt> <Et>
en|MMMEd|<Et>, <Mt> <dn>
en|MMMEEEE|<Mt> <Et>
en|MMMEEEEd|<Et>, <Mt> <dn>
en|MMMM|<Mt>
en|MMMMd|<Mt> <dn>
en|MMMME|<Et> (month: <Mt>)
en|MMMMEd|<Et>, <Mt> <dn>
en|MMMMEEEE|<Mt> <Et>
en|MMMMEEEEd|<Et>, <Mt> <dn>
en|y|<yn>
en|yd|<yn> (day: <dn>)
en|yE|<yn> <Et>
en|yEd|<dn> <Et> <yn>
en|yEEEE|<yn> <Et>
en|yEEEEd|<dn> <Et> <yn>
en|yM|<Mn>/<yn>
en|yMd|<Mn>/<dn>/<yn>
en|yME|<Mn>/<yn> <Et>
en|yMEd|<Et>, <Mn>/<dn>/<yn>
en|yMEEEE|<Mn>/<yn> <Et>
en|yMEEEEd|<Et>, <Mn>/<dn>/<yn>
en|yMM|<MN>/<yn>
en|yMMd|<MN>/<dn>/<yn>
en|yMME|<MN>/<yn> <Et>
en|yMMEd|<Et>, <MN>/<dn>/<yn>
en|yMMEEEE|<MN>/<yn> <Et>
en|yMMEEEEd|<Et>, <MN>/<dn>/<yn>
en|yMMM|<Mt> <yn>
en|yMMMd|<Mt> <dn>, <yn>
en|yMMME|<Mt> <yn> <Et>
en|yMMMEd|<Et>, <Mt> <dn>, <yn>
en|yMMMEEEE|<Mt> <yn> <Et>
en|yMMMEEEEd|<Et>, <Mt> <dn>, <yn>
en|yMMMM|<Mt> <yn>
en|yMMMMd|<Mt> <dn>, <yn>
en|yMMMME|<Mt> <yn> <Et>
en|yMMMMEd|<Et>, <Mt> <dn>, <yn>
en|yMMMMEEEE|<Mt> <yn> <Et>
en|yMMMMEEEEd|<Et>, <Mt> <dn>, <yn>
en|Gy|<yn> <Gt>
en|GyMMMd|<Mt> <dn>, <yn> <Gt>
en|GyMMMMEEEEd|<Et>, <Mt> <dn>, <yn> <Gt>
en|GyMd|<Mn>/<dn>/<yn> <Gt>
en|s|<sn>
en|sz|<sn> <zt>
en|szzzz|<sn> <zt>
en|m|<mn>
en|mz|<mn> <zt>
en|mzzzz|<mn> <zt>
en|ms|<mN>:<sN>
en|msz|<mN>:<sN> <zt>
en|mszzzz|<mN>:<sN> <zt>
en|h|<hn> <at>
en|hz|<hn> <at> <zt>
en|hzzzz|<hn> <at> <zt>
en|hs|<hn> <at> (second: <sn>)
en|hsz|<hn> <at> <zt> (second: <sn>)
en|hszzzz|<hn> <at> <zt> (second: <sn>)
en|hm|<hn>:<mN> <at>
en|hmz|<hn>:<mN> <at> <zt>
en|hmzzzz|<hn>:<mN> <at> <zt>
en|hms|<hn>:<mN>:<sN> <at>
en|hmsz|<hn>:<mN>:<sN> <at> <zt>
en|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
en|H|<hN>
en|Hz|<hN>h <zt>
en|Hzzzz|<hN>h <zt>
en|Hs|<hN> (second: <sn>)
en|Hsz|<hN>h <zt> (second: <sn>)
en|Hszzzz|<hN>h <zt> (second: <sn>)
en|Hm|<hN>:<mN>
en|Hmz|<hN>:<mN> <zt>
en|Hmzzzz|<hN>:<mN> <zt>
en|Hms|<hN>:<mN>:<sN>
en|Hmsz|<hN>:<mN>:<sN> <zt>
en|Hmszzzz|<hN>:<mN>:<sN> <zt>
en|yMMMdh|<Mt> <dn>, <yn>, <hn> <at>
en|yMMMdhm|<Mt> <dn>, <yn>, <hn>:<mN> <at>
en|yMMMdHm|<Mt> <dn>, <yn>, <hN>:<mN>
en|yMMMdhmz|<Mt> <dn>, <yn>, <hn>:<mN> <at> <zt>
en|yMMMdHms|<Mt> <dn>, <yn>, <hN>:<mN>:<sN>
en|yMMMdHmzzzz|<Mt> <dn>, <yn>, <hN>:<mN> <zt>
en|MMMEdh|<Et>, <Mt> <dn>, <hn> <at>
en|MMMEdhm|<Et>, <Mt> <dn>, <hn>:<mN> <at>
en|MMMEdHm|<Et>, <Mt> <dn>, <hN>:<mN>
en|MMMEdhmz|<Et>, <Mt> <dn>, <hn>:<mN> <at> <zt>
en|MMMEdHms|<Et>, <Mt> <dn>, <hN>:<mN>:<sN>
en|MMMEdHmzzzz|<Et>, <Mt> <dn>, <hN>:<mN> <zt>
en|yMdh|<Mn>/<dn>/<yn>, <hn> <at>
en|yMdhm|<Mn>/<dn>/<yn>, <hn>:<mN> <at>
en|yMdHm|<Mn>/<dn>/<yn>, <hN>:<mN>
en|yMdhmz|<Mn>/<dn>/<yn>, <hn>:<mN> <at> <zt>
en|yMdHms|<Mn>/<dn>/<yn>, <hN>:<mN>:<sN>
en|yMdHmzzzz|<Mn>/<dn>/<yn>, <hN>:<mN> <zt>
en|Eh|<Et> <hn> <at>
en|Ehm|<Et> <hn>:<mN> <at>
en|EHm|<Et> <hN>:<mN>
en|Ehmz|<Et>, <hn>:<mN> <at> <zt>
en|EHms|<Et> <hN>:<mN>:<sN>
en|EHmzzzz|<Et>, <hN>:<mN> <zt>
en|dh|<dn>, <hn> <at>
en|dhm|<dn>, <hn>:<mN> <at>
en|dHm|<dn>, <hN>:<mN>
en|dhmz|<dn>, <hn>:<mN> <at> <zt>
en|dHms|<dn>, <hN>:<mN>:<sN>
en|dHmzzzz|<dn>, <hN>:<mN> <zt>
en|MMMMdh|<Mt> <dn> at <hn> <at>
en|MMMMdhm|<Mt> <dn> at <hn>:<mN> <at>
en|MMMMdHm|<Mt> <dn> at <hN>:<mN>
en|MMMMdhmz|<Mt> <dn> at <hn>:<mN> <at> <zt>
en|MMMMdHms|<Mt> <dn> at <hN>:<mN>:<sN>
en|MMMMdHmzzzz|<Mt> <dn> at <hN>:<mN> <zt>
en|yMMMMEEEEdh|<Et>, <Mt> <dn>, <yn> at <hn> <at>
en|yMMMMEEEEdhm|<Et>, <Mt> <dn>, <yn> at <hn>:<mN> <at>
en|yMMMMEEEEdHm|<Et>, <Mt> <dn>, <yn> at <hN>:<mN>
en|yMMMMEEEEdhmz|<Et>, <Mt> <dn>, <yn> at <hn>:<mN> <at> <zt>
en|yMMMMEEEEdHms|<Et>, <Mt> <dn>, <yn> at <hN>:<mN>:<sN>
en|yMMMMEEEEdHmzzzz|<Et>, <Mt> <dn>, <yn> at <hN>:<mN> <zt>
en|EEEEdh|<dn> <Et>, <hn> <at>
en|EEEEdhm|<dn> <Et>, <hn>:<mN> <at>
en|EEEEdHm|<dn> <Et>, <hN>:<mN>
en|EEEEdhmz|<dn> <Et>, <hn>:<mN> <at> <zt>
en|EEEEdHms|<dn> <Et>, <hN>:<mN>:<sN>
en|EEEEdHmzzzz|<dn> <Et>, <hN>:<mN> <zt>
en-GB|d|<dn>
en-GB|E|<Et>
en-GB|Ed|<Et> <dn>
en-GB|EEEE|<Et>
en-GB|EEEEd|<Et> <dn>
en-GB|M|<Mn>
en-GB|Md|<dN>/<MN>
en-GB|ME|<Mn> <Et>
en-GB|MEd|<Et> <dN>/<MN>
en-GB|MEEEE|<Mn> <Et>
en-GB|MEEEEd|<Et> <dN>/<MN>
en-GB|MM|<MN>
en-GB|MMd|<dn>/<MN>
en-GB|MME|<Et> (month: <MN>)
en-GB|MMEd|<Et> <dN>/<MN>
en-GB|MMEEEE|<MN> <Et>
en-GB|MMEEEEd|<Et> <dN>/<MN>
en-GB|MMM|<Mt>
en-GB|MMMd|<dn> <Mt>
en-GB|MMME|<Mt> <Et>
en-GB|MMMEd|<Et> <dn> <Mt>
en-GB|MMMEEEE|<Mt> <Et>
en-GB|MMMEEEEd|<Et> <dn> <Mt>
en-GB|MMMM|<Mt>
en-GB|MMMMd|<dn> <Mt>
en-GB|MMMME|<Et> (month: <Mt>)
en-GB|MMMMEd|<Et> <dn> <Mt>
en-GB|MMMMEEEE|<Mt> <Et>
en-GB|MMMMEEEEd|<Et> <dn> <Mt>
en-GB|y|<yn>
en-GB|yd|<yn> (day: <dn>)
en-GB|yE|<yn> <Et>
en-GB|yEd|<Et> <dn> <yn>
en-GB|yEEEE|<yn> <Et>
en-GB|yEEEEd|<Et> <dn> <yn>
en-GB|yM|<MN>/<yn>
en-GB|yMd|<dN>/<MN>/<yn>
en-GB|yME|<MN>/<yn> <Et>
en-GB|yMEd|<Et>, <dN>/<MN>/<yn>
en-GB|yMEEEE|<MN>/<yn> <Et>
en-GB|yMEEEEd|<Et>, <dN>/<MN>/<yn>
en-GB|yMM|<MN>/<yn>
en-GB|yMMd|<dn>/<MN>/<yn>
en-GB|yMME|<MN>/<yn> <Et>
en-GB|yMMEd|<Et>, <dN>/<MN>/<yn>
en-GB|yMMEEEE|<MN>/<yn> <Et>
en-GB|yMMEEEEd|<Et>, <dN>/<MN>/<yn>
en-GB|yMMM|<Mt> <yn>
en-GB|yMMMd|<dn> <Mt> <yn>
en-GB|yMMME|<Mt> <yn> <Et>
en-GB|yMMMEd|<Et>, <dn> <Mt> <yn>
en-GB|yMMMEEEE|<Mt> <yn> <Et>
en-GB|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
en-GB|yMMMM|<Mt> <yn>
en-GB|yMMMMd|<dn> <Mt> <yn>
en-GB|yMMMME|<Mt> <yn> <Et>
en-GB|yMMMMEd|<Et>, <dn> <Mt> <yn>
en-GB|yMMMMEEEE|<Mt> <yn> <Et>
en-GB|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
en-GB|Gy|<yn> <Gt>
en-GB|GyMMMd|<dn> <Mt> <yn> <Gt>
en-GB|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
en-GB|GyMd|<dN>/<MN>/<yn> <Gt>
en-GB|s|<sn>
en-GB|sz|<sn> <zt>
en-GB|szzzz|<sn> <zt>
en-GB|m|<mn>
en-GB|mz|<mn> <zt>
en-GB|mzzzz|<mn> <zt>
en-GB|ms|<mN>:<sN>
en-GB|msz|<mN>:<sN> <zt>
en-GB|mszzzz|<mN>:<sN> <zt>
en-GB|h|<hn> <at>
en-GB|hz|<hn> <at> <zt>
en-GB|hzzzz|<hn> <at> <zt>
en-GB|hs|<hn> <at> (second: <sn>)
en-GB|hsz|<hn> <at> <zt> (second: <sn>)
en-GB|hszzzz|<hn> <at> <zt> (second: <sn>)
en-GB|hm|<hn>:<mN> <at>
en-GB|hmz|<hn>:<mN> <at> <zt>
en-GB|hmzzzz|<hn>:<mN> <at> <zt>
en-GB|hms|<hn>:<mN>:<sN> <at>
en-GB|hmsz|<hn>:<mN>:<sN> <at> <zt>
en-GB|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
en-GB|H|<hN>
en-GB|Hz|<hN>h <zt>
en-GB|Hzzzz|<hN>h <zt>
en-GB|Hs|<hN> (second: <sn>)
en-GB|Hsz|<hN>h <zt> (second: <sn>)
en-GB|Hszzzz|<hN>h <zt> (second: <sn>)
en-GB|Hm|<hN>:<mN>
en-GB|Hmz|<hN>:<mN> <zt>
en-GB|Hmzzzz|<hN>:<mN> <zt>
en-GB|Hms|<hN>:<mN>:<sN>
en-GB|Hmsz|<hn>:<mN>:<sN> <zt>
en-GB|Hmszzzz|<hn>:<mN>:<sN> <zt>
en-GB|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
en-GB|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
en-GB|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
en-GB|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
en-GB|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
en-GB|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
en-GB|MMMEdh|<Et> <dn> <Mt>, <hn> <at>
en-GB|MMMEdhm|<Et> <dn> <Mt>, <hn>:<mN> <at>
en-GB|MMMEdHm|<Et> <dn> <Mt>, <hN>:<mN>
en-GB|MMMEdhmz|<Et> <dn> <Mt>, <hn>:<mN> <at> <zt>
en-GB|MMMEdHms|<Et> <dn> <Mt>, <hN>:<mN>:<sN>
en-GB|MMMEdHmzzzz|<Et> <dn> <Mt>, <hN>:<mN> <zt>
en-GB|yMdh|<dN>/<MN>/<yn>, <hn> <at>
en-GB|yMdhm|<dN>/<MN>/<yn>, <hn>:<mN> <at>
en-GB|yMdHm|<dN>/<MN>/<yn>, <hN>:<mN>
en-GB|yMdhmz|<dN>/<MN>/<yn>, <hn>:<mN> <at> <zt>
en-GB|yMdHms|<dN>/<MN>/<yn>, <hN>:<mN>:<sN>
en-GB|yMdHmzzzz|<dN>/<MN>/<yn>, <hN>:<mN> <zt>
en-GB|Eh|<Et> <hn> <at>
en-GB|Ehm|<Et> <hn>:<mN> <at>
en-GB|EHm|<Et> <hN>:<mN>
en-GB|Ehmz|<Et>, <hn>:<mN> <at> <zt>
en-GB|EHms|<Et> <hN>:<mN>:<sN>
en-GB|EHmzzzz|<Et>, <hN>:<mN> <zt>
en-GB|dh|<dn>, <hn> <at>
en-GB|dhm|<dn>, <hn>:<mN> <at>
en-GB|dHm|<dn>, <hN>:<mN>
en-GB|dhmz|<dn>, <hn>:<mN> <at> <zt>
en-GB|dHms|<dn>, <hN>:<mN>:<sN>
en-GB|dHmzzzz|<dn>, <hN>:<mN> <zt>
en-GB|MMMMdh|<dn> <Mt> at <hn> <at>
en-GB|MMMMdhm|<dn> <Mt> at <hn>:<mN> <at>
en-GB|MMMMdHm|<dn> <Mt> at <hN>:<mN>
en-GB|MMMMdhmz|<dn> <Mt> at <hn>:<mN> <at> <zt>
en-GB|MMMMdHms|<dn> <Mt> at <hN>:<mN>:<sN>
en-GB|MMMMdHmzzzz|<dn> <Mt> at <hN>:<mN> <zt>
en-GB|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> at <hn> <at>
en-GB|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> at <hn>:<mN> <at>
en-GB|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> at <hN>:<mN>
en-GB|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> at <hn>:<mN> <at> <zt>
en-GB|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> at <hN>:<mN>:<sN>
en-GB|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> at <hN>:<mN> <zt>
en-GB|EEEEdh|<Et> <dn>, <hn> <at>
en-GB|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
en-GB|EEEEdHm|<Et> <dn>, <hN>:<mN>
en-GB|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
en-GB|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
en-GB|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
en-AU|d|<dn>
en-AU|E|<Et>
en-AU|Ed|<Et> <dn>
en-AU|EEEE|<Et>
en-AU|EEEEd|<Et> <dn>
en-AU|M|<Mn>
en-AU|Md|<dn>/<Mn>
en-AU|ME|<Mn> <Et>
en-AU|MEd|<Et>, <dn>/<Mn>
en-AU|MEEEE|<Mn> <Et>
en-AU|MEEEEd|<Et>, <dn>/<Mn>
en-AU|MM|<MN>
en-AU|MMd|<dn>/<MN>
en-AU|MME|<Et> (month: <MN>)
en-AU|MMEd|<Et>, <dn>/<MN>
en-AU|MMEEEE|<MN> <Et>
en-AU|MMEEEEd|<Et>, <dn>/<MN>
en-AU|MMM|<Mt>
en-AU|MMMd|<dn> <Mt>
en-AU|MMME|<Mt> <Et>
en-AU|MMMEd|<Et>, <dn> <Mt>
en-AU|MMMEEEE|<Mt> <Et>
en-AU|MMMEEEEd|<Et> <dn> <Mt>
en-AU|MMMM|<Mt>
en-AU|MMMMd|<dn> <Mt>
en-AU|MMMME|<Et> (month: <Mt>)
en-AU|MMMMEd|<Et> <dn> <Mt>
en-AU|MMMMEEEE|<Mt> <Et>
en-AU|MMMMEEEEd|<Et> <dn> <Mt>
en-AU|y|<yn>
en-AU|yd|<yn> (day: <dn>)
en-AU|yE|<yn> <Et>
en-AU|yEd|<Et> <dn> <yn>
en-AU|yEEEE|<yn> <Et>
en-AU|yEEEEd|<Et> <dn> <yn>
en-AU|yM|<MN>/<yn>
en-AU|yMd|<dN>/<MN>/<yn>
en-AU|yME|<MN>/<yn> <Et>
en-AU|yMEd|<Et>, <dN>/<MN>/<yn>
en-AU|yMEEEE|<MN>/<yn> <Et>
en-AU|yMEEEEd|<Et>, <dN>/<MN>/<yn>
en-AU|yMM|<MN>/<yn>
en-AU|yMMd|<dN>/<MN>/<yn>
en-AU|yMME|<MN>/<yn> <Et>
en-AU|yMMEd|<Et>, <dN>/<MN>/<yn>
en-AU|yMMEEEE|<MN>/<yn> <Et>
en-AU|yMMEEEEd|<Et>, <dN>/<MN>/<yn>
en-AU|yMMM|<Mt> <yn>
en-AU|yMMMd|<dn> <Mt> <yn>
en-AU|yMMME|<Mt> <yn> <Et>
en-AU|yMMMEd|<Et>, <dn> <Mt> <yn>
en-AU|yMMMEEEE|<Mt> <yn> <Et>
en-AU|yMMMEEEEd|<Et> <dn> <Mt> <yn>
en-AU|yMMMM|<Mt> <yn>
en-AU|yMMMMd|<dn> <Mt> <yn>
en-AU|yMMMME|<Mt> <yn> <Et>
en-AU|yMMMMEd|<Et> <dn> <Mt> <yn>
en-AU|yMMMMEEEE|<Mt> <yn> <Et>
en-AU|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
en-AU|Gy|<yn> <Gt>
en-AU|GyMMMd|<dn> <Mt> <yn> <Gt>
en-AU|GyMMMMEEEEd|<Et> <dn> <Mt> <yn> <Gt>
en-AU|GyMd|<dN>/<MN>/<yn> <Gt>
en-AU|s|<sn>
en-AU|sz|<sn> <zt>
en-AU|szzzz|<sn> <zt>
en-AU|m|<mn>
en-AU|mz|<mn> <zt>
en-AU|mzzzz|<mn> <zt>
en-AU|ms|<mN>:<sN>
en-AU|msz|<mN>:<sN> <zt>
en-AU|mszzzz|<mN>:<sN> <zt>
en-AU|h|<hn> <at>
en-AU|hz|<hn> <at> <zt>
en-AU|hzzzz|<hn> <at> <zt>
en-AU|hs|<hn> <at> (second: <sn>)
en-AU|hsz|<hn> <at> <zt> (second: <sn>)
en-AU|hszzzz|<hn> <at> <zt> (second: <sn>)
en-AU|hm|<hn>:<mN> <at>
en-AU|hmz|<hn>:<mN> <at> <zt>
en-AU|hmzzzz|<hn>:<mN> <at> <zt>
en-AU|hms|<hn>:<mN>:<sN> <at>
en-AU|hmsz|<hn>:<mN>:<sN> <at> <zt>
en-AU|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
en-AU|H|<hN>
en-AU|Hz|<hN>h <zt>
en-AU|Hzzzz|<hN>h <zt>
en-AU|Hs|<hN> (second: <sn>)
en-AU|Hsz|<hN>h <zt> (second: <sn>)
en-AU|Hszzzz|<hN>h <zt> (second: <sn>)
en-AU|Hm|<hN>:<mN>
en-AU|Hmz|<hN>:<mN> <zt>
en-AU|Hmzzzz|<hN>:<mN> <zt>
en-AU|Hms|<hN>:<mN>:<sN>
en-AU|Hmsz|<hN>:<mN>:<sN> <zt>
en-AU|Hmszzzz|<hN>:<mN>:<sN> <zt>
en-AU|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
en-AU|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
en-AU|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
en-AU|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
en-AU|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
en-AU|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
en-AU|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
en-AU|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
en-AU|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
en-AU|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
en-AU|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
en-AU|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
en-AU|yMdh|<dN>/<MN>/<yn>, <hn> <at>
en-AU|yMdhm|<dN>/<MN>/<yn>, <hn>:<mN> <at>
en-AU|yMdHm|<dN>/<MN>/<yn>, <hN>:<mN>
en-AU|yMdhmz|<dN>/<MN>/<yn>, <hn>:<mN> <at> <zt>
en-AU|yMdHms|<dN>/<MN>/<yn>, <hN>:<mN>:<sN>
en-AU|yMdHmzzzz|<dN>/<MN>/<yn>, <hN>:<mN> <zt>
en-AU|Eh|<Et> <hn> <at>
en-AU|Ehm|<Et> <hn>:<mN> <at>
en-AU|EHm|<Et> <hN>:<mN>
en-AU|Ehmz|<Et>, <hn>:<mN> <at> <zt>
en-AU|EHms|<Et> <hN>:<mN>:<sN>
en-AU|EHmzzzz|<Et>, <hN>:<mN> <zt>
en-AU|dh|<dn>, <hn> <at>
en-AU|dhm|<dn>, <hn>:<mN> <at>
en-AU|dHm|<dn>, <hN>:<mN>
en-AU|dhmz|<dn>, <hn>:<mN> <at> <zt>
en-AU|dHms|<dn>, <hN>:<mN>:<sN>
en-AU|dHmzzzz|<dn>, <hN>:<mN> <zt>
en-AU|MMMMdh|<dn> <Mt> at <hn> <at>
en-AU|MMMMdhm|<dn> <Mt> at <hn>:<mN> <at>
en-AU|MMMMdHm|<dn> <Mt> at <hN>:<mN>
en-AU|MMMMdhmz|<dn> <Mt> at <hn>:<mN> <at> <zt>
en-AU|MMMMdHms|<dn> <Mt> at <hN>:<mN>:<sN>
en-AU|MMMMdHmzzzz|<dn> <Mt> at <hN>:<mN> <zt>
en-AU|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> at <hn> <at>
en-AU|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> at <hn>:<mN> <at>
en-AU|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> at <hN>:<mN>
en-AU|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> at <hn>:<mN> <at> <zt>
en-AU|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> at <hN>:<mN>:<sN>
en-AU|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> at <hN>:<mN> <zt>
en-AU|EEEEdh|<Et> <dn>, <hn> <at>
en-AU|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
en-AU|EEEEdHm|<Et> <dn>, <hN>:<mN>
en-AU|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
en-AU|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
en-AU|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
en-NZ|d|<dn>
en-NZ|E|<Et>
en-NZ|Ed|<Et> <dn>
en-NZ|EEEE|<Et>
en-NZ|EEEEd|<Et> <dn>
en-NZ|M|<Mn>
en-NZ|Md|<dn>/<Mn>
en-NZ|ME|<Mn> <Et>
en-NZ|MEd|<Et>, <dN>/<MN>
en-NZ|MEEEE|<Mn> <Et>
en-NZ|MEEEEd|<Et>, <dN>/<MN>
en-NZ|MM|<MN>
en-NZ|MMd|<dn>/<MN>
en-NZ|MME|<Et> (month: <MN>)
en-NZ|MMEd|<Et>, <dN>/<MN>
en-NZ|MMEEEE|<MN> <Et>
en-NZ|MMEEEEd|<Et>, <dN>/<MN>
en-NZ|MMM|<Mt>
en-NZ|MMMd|<dn> <Mt>
en-NZ|MMME|<Mt> <Et>
en-NZ|MMMEd|<Et>, <dn> <Mt>
en-NZ|MMMEEEE|<Mt> <Et>
en-NZ|MMMEEEEd|<Et>, <dn> <Mt>
en-NZ|MMMM|<Mt>
en-NZ|MMMMd|<dn> <Mt>
en-NZ|MMMME|<Et> (month: <Mt>)
en-NZ|MMMMEd|<Et>, <dn> <Mt>
en-NZ|MMMMEEEE|<Mt> <Et>
en-NZ|MMMMEEEEd|<Et>, <dn> <Mt>
en-NZ|y|<yn>
en-NZ|yd|<yn> (day: <dn>)
en-NZ|yE|<yn> <Et>
en-NZ|yEd|<Et> <dn> <yn>
en-NZ|yEEEE|<yn> <Et>
en-NZ|yEEEEd|<Et> <dn> <yn>
en-NZ|yM|<MN>/<yn>
en-NZ|yMd|<dn>/<MN>/<yn>
en-NZ|yME|<MN>/<yn> <Et>
en-NZ|yMEd|<Et>, <dN>/<MN>/<yn>
en-NZ|yMEEEE|<MN>/<yn> <Et>
en-NZ|yMEEEEd|<Et>, <dN>/<MN>/<yn>
en-NZ|yMM|<MN>/<yn>
en-NZ|yMMd|<dn>/<MN>/<yn>
en-NZ|yMME|<MN>/<yn> <Et>
en-NZ|yMMEd|<Et>, <dN>/<MN>/<yn>
en-NZ|yMMEEEE|<MN>/<yn> <Et>
en-NZ|yMMEEEEd|<Et>, <dN>/<MN>/<yn>
en-NZ|yMMM|<Mt> <yn>
en-NZ|yMMMd|<dn> <Mt> <yn>
en-NZ|yMMME|<Mt> <yn> <Et>
en-NZ|yMMMEd|<Et>, <dn> <Mt> <yn>
en-NZ|yMMMEEEE|<Mt> <yn> <Et>
en-NZ|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
en-NZ|yMMMM|<Mt> <yn>
en-NZ|yMMMMd|<dn> <Mt> <yn>
en-NZ|yMMMME|<Mt> <yn> <Et>
en-NZ|yMMMMEd|<Et>, <dn> <Mt> <yn>
en-NZ|yMMMMEEEE|<Mt> <yn> <Et>
en-NZ|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
en-NZ|Gy|<yn> <Gt>
en-NZ|GyMMMd|<dn> <Mt> <yn> <Gt>
en-NZ|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
en-NZ|GyMd|<dN>/<MN>/<yn> <Gt>
en-NZ|s|<sn>
en-NZ|sz|<sn> <zt>
en-NZ|szzzz|<sn> <zt>
en-NZ|m|<mn>
en-NZ|mz|<mn> <zt>
en-NZ|mzzzz|<mn> <zt>
en-NZ|ms|<mN>:<sN>
en-NZ|msz|<mN>:<sN> <zt>
en-NZ|mszzzz|<mN>:<sN> <zt>
en-NZ|h|<hn> <at>
en-NZ|hz|<hn> <at> <zt>
en-NZ|hzzzz|<hn> <at> <zt>
en-NZ|hs|<hn> <at> (second: <sn>)
en-NZ|hsz|<hn> <at> <zt> (second: <sn>)
en-NZ|hszzzz|<hn> <at> <zt> (second: <sn>)
en-NZ|hm|<hn>:<mN> <at>
en-NZ|hmz|<hn>:<mN> <at> <zt>
en-NZ|hmzzzz|<hn>:<mN> <at> <zt>
en-NZ|hms|<hn>:<mN>:<sN> <at>
en-NZ|hmsz|<hn>:<mN>:<sN> <at> <zt>
en-NZ|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
en-NZ|H|<hN>
en-NZ|Hz|<hN>h <zt>
en-NZ|Hzzzz|<hN>h <zt>
en-NZ|Hs|<hN> (second: <sn>)
en-NZ|Hsz|<hN>h <zt> (second: <sn>)
en-NZ|Hszzzz|<hN>h <zt> (second: <sn>)
en-NZ|Hm|<hN>:<mN>
en-NZ|Hmz|<hN>:<mN> <zt>
en-NZ|Hmzzzz|<hN>:<mN> <zt>
en-NZ|Hms|<hN>:<mN>:<sN>
en-NZ|Hmsz|<hN>:<mN>:<sN> <zt>
en-NZ|Hmszzzz|<hN>:<mN>:<sN> <zt>
en-NZ|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
en-NZ|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
en-NZ|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
en-NZ|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
en-NZ|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
en-NZ|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
en-NZ|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
en-NZ|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
en-NZ|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
en-NZ|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
en-NZ|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
en-NZ|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
en-NZ|yMdh|<dn>/<MN>/<yn>, <hn> <at>
en-NZ|yMdhm|<dn>/<MN>/<yn>, <hn>:<mN> <at>
en-NZ|yMdHm|<dn>/<MN>/<yn>, <hN>:<mN>
en-NZ|yMdhmz|<dn>/<MN>/<yn>, <hn>:<mN> <at> <zt>
en-NZ|yMdHms|<dn>/<MN>/<yn>, <hN>:<mN>:<sN>
en-NZ|yMdHmzzzz|<dn>/<MN>/<yn>, <hN>:<mN> <zt>
en-NZ|Eh|<Et> <hn> <at>
en-NZ|Ehm|<Et> <hn>:<mN> <at>
en-NZ|EHm|<Et> <hN>:<mN>
en-NZ|Ehmz|<Et>, <hn>:<mN> <at> <zt>
en-NZ|EHms|<Et> <hN>:<mN>:<sN>
en-NZ|EHmzzzz|<Et>, <hN>:<mN> <zt>
en-NZ|dh|<dn>, <hn> <at>
en-NZ|dhm|<dn>, <hn>:<mN> <at>
en-NZ|dHm|<dn>, <hN>:<mN>
en-NZ|dhmz|<dn>, <hn>:<mN> <at> <zt>
en-NZ|dHms|<dn>, <hN>:<mN>:<sN>
en-NZ|dHmzzzz|<dn>, <hN>:<mN> <zt>
en-NZ|MMMMdh|<dn> <Mt> at <hn> <at>
en-NZ|MMMMdhm|<dn> <Mt> at <hn>:<mN> <at>
en-NZ|MMMMdHm|<dn> <Mt> at <hN>:<mN>
en-NZ|MMMMdhmz|<dn> <Mt> at <hn>:<mN> <at> <zt>
en-NZ|MMMMdHms|<dn> <Mt> at <hN>:<mN>:<sN>
en-NZ|MMMMdHmzzzz|<dn> <Mt> at <hN>:<mN> <zt>
en-NZ|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> at <hn> <at>
en-NZ|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> at <hn>:<mN> <at>
en-NZ|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> at <hN>:<mN>
en-NZ|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> at <hn>:<mN> <at> <zt>
en-NZ|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> at <hN>:<mN>:<sN>
en-NZ|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> at <hN>:<mN> <zt>
en-NZ|EEEEdh|<Et> <dn>, <hn> <at>
en-NZ|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
en-NZ|EEEEdHm|<Et> <dn>, <hN>:<mN>
en-NZ|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
en-NZ|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
en-NZ|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
en-IE|d|<dn>
en-IE|E|<Et>
en-IE|Ed|<Et> <dn>
en-IE|EEEE|<Et>
en-IE|EEEEd|<Et> <dn>
en-IE|M|<Mn>
en-IE|Md|<dn>/<Mn>
en-IE|ME|<Mn> <Et>
en-IE|MEd|<Et>, <dn>/<Mn>
en-IE|MEEEE|<Mn> <Et>
en-IE|MEEEEd|<Et>, <dn>/<Mn>
en-IE|MM|<MN>
en-IE|MMd|<dn>/<MN>
en-IE|MME|<Et> (month: <MN>)
en-IE|MMEd|<Et>, <dn>/<MN>
en-IE|MMEEEE|<MN> <Et>
en-IE|MMEEEEd|<Et>, <dn>/<MN>
en-IE|MMM|<Mt>
en-IE|MMMd|<dn> <Mt>
en-IE|MMME|<Mt> <Et>
en-IE|MMMEd|<Et>, <dn> <Mt>
en-IE|MMMEEEE|<Mt> <Et>
en-IE|MMMEEEEd|<Et>, <dn> <Mt>
en-IE|MMMM|<Mt>
en-IE|MMMMd|<dn> <Mt>
en-IE|MMMME|<Et> (month: <Mt>)
en-IE|MMMMEd|<Et>, <dn> <Mt>
en-IE|MMMMEEEE|<Mt> <Et>
en-IE|MMMMEEEEd|<Et>, <dn> <Mt>
en-IE|y|<yn>
en-IE|yd|<yn> (day: <dn>)
en-IE|yE|<yn> <Et>
en-IE|yEd|<Et> <dn> <yn>
en-IE|yEEEE|<yn> <Et>
en-IE|yEEEEd|<Et> <dn> <yn>
en-IE|yM|<MN>/<yn>
en-IE|yMd|<dn>/<Mn>/<yn>
en-IE|yME|<MN>/<yn> <Et>
en-IE|yMEd|<Et>, <dn>/<Mn>/<yn>
en-IE|yMEEEE|<MN>/<yn> <Et>
en-IE|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
en-IE|yMM|<MN>/<yn>
en-IE|yMMd|<dn>/<MN>/<yn>
en-IE|yMME|<MN>/<yn> <Et>
en-IE|yMMEd|<Et>, <dn>/<MN>/<yn>
en-IE|yMMEEEE|<MN>/<yn> <Et>
en-IE|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
en-IE|yMMM|<Mt> <yn>
en-IE|yMMMd|<dn> <Mt> <yn>
en-IE|yMMME|<Mt> <yn> <Et>
en-IE|yMMMEd|<Et> <dn> <Mt> <yn>
en-IE|yMMMEEEE|<Mt> <yn> <Et>
en-IE|yMMMEEEEd|<Et> <dn> <Mt> <yn>
en-IE|yMMMM|<Mt> <yn>
en-IE|yMMMMd|<dn> <Mt> <yn>
en-IE|yMMMME|<Mt> <yn> <Et>
en-IE|yMMMMEd|<Et> <dn> <Mt> <yn>
en-IE|yMMMMEEEE|<Mt> <yn> <Et>
en-IE|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
en-IE|Gy|<yn> <Gt>
en-IE|GyMMMd|<dn> <Mt> <yn> <Gt>
en-IE|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
en-IE|GyMd|<dN>/<MN>/<yn> <Gt>
en-IE|s|<sn>
en-IE|sz|<sn> <zt>
en-IE|szzzz|<sn> <zt>
en-IE|m|<mn>
en-IE|mz|<mn> <zt>
en-IE|mzzzz|<mn> <zt>
en-IE|ms|<mN>:<sN>
en-IE|msz|<mN>:<sN> <zt>
en-IE|mszzzz|<mN>:<sN> <zt>
en-IE|h|<hn> <at>
en-IE|hz|<hn> <at> <zt>
en-IE|hzzzz|<hn> <at> <zt>
en-IE|hs|<hn> <at> (second: <sn>)
en-IE|hsz|<hn> <at> <zt> (second: <sn>)
en-IE|hszzzz|<hn> <at> <zt> (second: <sn>)
en-IE|hm|<hn>:<mN> <at>
en-IE|hmz|<hn>:<mN> <at> <zt>
en-IE|hmzzzz|<hn>:<mN> <at> <zt>
en-IE|hms|<hn>:<mN>:<sN> <at>
en-IE|hmsz|<hn>:<mN>:<sN> <at> <zt>
en-IE|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
en-IE|H|<hN>
en-IE|Hz|<hN>h <zt>
en-IE|Hzzzz|<hN>h <zt>
en-IE|Hs|<hN> (second: <sn>)
en-IE|Hsz|<hN>h <zt> (second: <sn>)
en-IE|Hszzzz|<hN>h <zt> (second: <sn>)
en-IE|Hm|<hN>:<mN>
en-IE|Hmz|<hN>:<mN> <zt>
en-IE|Hmzzzz|<hN>:<mN> <zt>
en-IE|Hms|<hN>:<mN>:<sN>
en-IE|Hmsz|<hn>:<mN>:<sN> <zt>
en-IE|Hmszzzz|<hn>:<mN>:<sN> <zt>
en-IE|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
en-IE|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
en-IE|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
en-IE|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
en-IE|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
en-IE|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
en-IE|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
en-IE|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
en-IE|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
en-IE|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
en-IE|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
en-IE|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
en-IE|yMdh|<dn>/<Mn>/<yn>, <hn> <at>
en-IE|yMdhm|<dn>/<Mn>/<yn>, <hn>:<mN> <at>
en-IE|yMdHm|<dn>/<Mn>/<yn>, <hN>:<mN>
en-IE|yMdhmz|<dn>/<Mn>/<yn>, <hn>:<mN> <at> <zt>
en-IE|yMdHms|<dn>/<Mn>/<yn>, <hN>:<mN>:<sN>
en-IE|yMdHmzzzz|<dn>/<Mn>/<yn>, <hN>:<mN> <zt>
en-IE|Eh|<Et> <hn> <at>
en-IE|Ehm|<Et> <hn>:<mN> <at>
en-IE|EHm|<Et> <hN>:<mN>
en-IE|Ehmz|<Et>, <hn>:<mN> <at> <zt>
en-IE|EHms|<Et> <hN>:<mN>:<sN>
en-IE|EHmzzzz|<Et>, <hN>:<mN> <zt>
en-IE|dh|<dn>, <hn> <at>
en-IE|dhm|<dn>, <hn>:<mN> <at>
en-IE|dHm|<dn>, <hN>:<mN>
en-IE|dhmz|<dn>, <hn>:<mN> <at> <zt>
en-IE|dHms|<dn>, <hN>:<mN>:<sN>
en-IE|dHmzzzz|<dn>, <hN>:<mN> <zt>
en-IE|MMMMdh|<dn> <Mt> at <hn> <at>
en-IE|MMMMdhm|<dn> <Mt> at <hn>:<mN> <at>
en-IE|MMMMdHm|<dn> <Mt> at <hN>:<mN>
en-IE|MMMMdhmz|<dn> <Mt> at <hn>:<mN> <at> <zt>
en-IE|MMMMdHms|<dn> <Mt> at <hN>:<mN>:<sN>
en-IE|MMMMdHmzzzz|<dn> <Mt> at <hN>:<mN> <zt>
en-IE|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> at <hn> <at>
en-IE|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> at <hn>:<mN> <at>
en-IE|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> at <hN>:<mN>
en-IE|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> at <hn>:<mN> <at> <zt>
en-IE|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> at <hN>:<mN>:<sN>
en-IE|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> at <hN>:<mN> <zt>
en-IE|EEEEdh|<Et> <dn>, <hn> <at>
en-IE|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
en-IE|EEEEdHm|<Et> <dn>, <hN>:<mN>
en-IE|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
en-IE|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
en-IE|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
en-IN|d|<dn>
en-IN|E|<Et>
en-IN|Ed|<Et> <dn>
en-IN|EEEE|<Et>
en-IN|EEEEd|<Et> <dn>
en-IN|M|<Mn>
en-IN|Md|<dN>/<MN>
en-IN|ME|<Mn> <Et>
en-IN|MEd|<Et>, <dN>/<MN>
en-IN|MEEEE|<Mn> <Et>
en-IN|MEEEEd|<Et>, <dN>/<MN>
en-IN|MM|<MN>
en-IN|MMd|<dn>/<MN>
en-IN|MME|<Et> (month: <MN>)
en-IN|MMEd|<Et>, <dN>/<MN>
en-IN|MMEEEE|<MN> <Et>
en-IN|MMEEEEd|<Et>, <dN>/<MN>
en-IN|MMM|<Mt>
en-IN|MMMd|<dn> <Mt>
en-IN|MMME|<Mt> <Et>
en-IN|MMMEd|<Et>, <dn> <Mt>
en-IN|MMMEEEE|<Mt> <Et>
en-IN|MMMEEEEd|<Et>, <dn> <Mt>
en-IN|MMMM|<Mt>
en-IN|MMMMd|<dn> <Mt>
en-IN|MMMME|<Et> (month: <Mt>)
en-IN|MMMMEd|<Et>, <dn> <Mt>
en-IN|MMMMEEEE|<Mt> <Et>
en-IN|MMMMEEEEd|<Et>, <dn> <Mt>
en-IN|y|<yn>
en-IN|yd|<yn> (day: <dn>)
en-IN|yE|<yn> <Et>
en-IN|yEd|<Et> <dn> <yn>
en-IN|yEEEE|<yn> <Et>
en-IN|yEEEEd|<Et> <dn> <yn>
en-IN|yM|<MN>/<yn>
en-IN|yMd|<dn>/<Mn>/<yn>
en-IN|yME|<MN>/<yn> <Et>
en-IN|yMEd|<Et>, <dn>/<Mn>/<yn>
en-IN|yMEEEE|<MN>/<yn> <Et>
en-IN|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
en-IN|yMM|<MN>/<yn>
en-IN|yMMd|<dn>/<MN>/<yn>
en-IN|yMME|<MN>/<yn> <Et>
en-IN|yMMEd|<Et>, <dn>/<MN>/<yn>
en-IN|yMMEEEE|<MN>/<yn> <Et>
en-IN|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
en-IN|yMMM|<Mt> <yn>
en-IN|yMMMd|<dn> <Mt> <yn>
en-IN|yMMME|<Mt> <yn> <Et>
en-IN|yMMMEd|<Et>, <dn> <Mt>, <yn>
en-IN|yMMMEEEE|<Mt> <yn> <Et>
en-IN|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
en-IN|yMMMM|<Mt> <yn>
en-IN|yMMMMd|<dn> <Mt> <yn>
en-IN|yMMMME|<Mt> <yn> <Et>
en-IN|yMMMMEd|<Et>, <dn> <Mt> <yn>
en-IN|yMMMMEEEE|<Mt> <yn> <Et>
en-IN|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
en-IN|Gy|<yn> <Gt>
en-IN|GyMMMd|<dn> <Mt> <yn> <Gt>
en-IN|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
en-IN|GyMd|<dN>/<MN>/<yn> <Gt>
en-IN|s|<sn>
en-IN|sz|<sn> <zt>
en-IN|szzzz|<sn> <zt>
en-IN|m|<mn>
en-IN|mz|<mn> <zt>
en-IN|mzzzz|<mn> <zt>
en-IN|ms|<mN>:<sN>
en-IN|msz|<mN>:<sN> <zt>
en-IN|mszzzz|<mN>:<sN> <zt>
en-IN|h|<hn> <at>
en-IN|hz|<hn> <at> <zt>
en-IN|hzzzz|<hn> <at> <zt>
en-IN|hs|<hn> <at> (second: <sn>)
en-IN|hsz|<hn> <at> <zt> (second: <sn>)
en-IN|hszzzz|<hn> <at> <zt> (second: <sn>)
en-IN|hm|<hn>:<mN> <at>
en-IN|hmz|<hn>:<mN> <at> <zt>
en-IN|hmzzzz|<hn>:<mN> <at> <zt>
en-IN|hms|<hn>:<mN>:<sN> <at>
en-IN|hmsz|<hn>:<mN>:<sN> <at> <zt>
en-IN|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
en-IN|H|<hN>
en-IN|Hz|<hN>h <zt>
en-IN|Hzzzz|<hN>h <zt>
en-IN|Hs|<hN> (second: <sn>)
en-IN|Hsz|<hN>h <zt> (second: <sn>)
en-IN|Hszzzz|<hN>h <zt> (second: <sn>)
en-IN|Hm|<hN>:<mN>
en-IN|Hmz|<hN>:<mN> <zt>
en-IN|Hmzzzz|<hN>:<mN> <zt>
en-IN|Hms|<hN>:<mN>:<sN>
en-IN|Hmsz|<hN>:<mN>:<sN> <zt>
en-IN|Hmszzzz|<hN>:<mN>:<sN> <zt>
en-IN|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
en-IN|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
en-IN|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
en-IN|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
en-IN|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
en-IN|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
en-IN|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
en-IN|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
en-IN|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
en-IN|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
en-IN|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
en-IN|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
en-IN|yMdh|<dn>/<Mn>/<yn>, <hn> <at>
en-IN|yMdhm|<dn>/<Mn>/<yn>, <hn>:<mN> <at>
en-IN|yMdHm|<dn>/<Mn>/<yn>, <hN>:<mN>
en-IN|yMdhmz|<dn>/<Mn>/<yn>, <hn>:<mN> <at> <zt>
en-IN|yMdHms|<dn>/<Mn>/<yn>, <hN>:<mN>:<sN>
en-IN|yMdHmzzzz|<dn>/<Mn>/<yn>, <hN>:<mN> <zt>
en-IN|Eh|<Et> <hn> <at>
en-IN|Ehm|<Et>, <hn>:<mN> <at>
en-IN|EHm|<Et>, <hN>:<mN>
en-IN|Ehmz|<Et>, <hn>:<mN> <at> <zt>
en-IN|EHms|<Et>, <hN>:<mN>:<sN>
en-IN|EHmzzzz|<Et>, <hN>:<mN> <zt>
en-IN|dh|<dn>, <hn> <at>
en-IN|dhm|<dn>, <hn>:<mN> <at>
en-IN|dHm|<dn>, <hN>:<mN>
en-IN|dhmz|<dn>, <hn>:<mN> <at> <zt>
en-IN|dHms|<dn>, <hN>:<mN>:<sN>
en-IN|dHmzzzz|<dn>, <hN>:<mN> <zt>
en-IN|MMMMdh|<dn> <Mt> at <hn> <at>
en-IN|MMMMdhm|<dn> <Mt> at <hn>:<mN> <at>
en-IN|MMMMdHm|<dn> <Mt> at <hN>:<mN>
en-IN|MMMMdhmz|<dn> <Mt> at <hn>:<mN> <at> <zt>
en-IN|MMMMdHms|<dn> <Mt> at <hN>:<mN>:<sN>
en-IN|MMMMdHmzzzz|<dn> <Mt> at <hN>:<mN> <zt>
en-IN|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> at <hn> <at>
en-IN|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> at <hn>:<mN> <at>
en-IN|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> at <hN>:<mN>
en-IN|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> at <hn>:<mN> <at> <zt>
en-IN|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> at <hN>:<mN>:<sN>
en-IN|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> at <hN>:<mN> <zt>
en-IN|EEEEdh|<Et> <dn>, <hn> <at>
en-IN|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
en-IN|EEEEdHm|<Et> <dn>, <hN>:<mN>
en-IN|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
en-IN|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
en-IN|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
en-ZA|d|<dn>
en-ZA|E|<Et>
en-ZA|Ed|<Et> <dn>
en-ZA|EEEE|<Et>
en-ZA|EEEEd|<Et> <dn>
en-ZA|M|<Mn>
en-ZA|Md|<MN>/<dN>
en-ZA|ME|<Mn> <Et>
en-ZA|MEd|<Et>, <MN>/<dN>
en-ZA|MEEEE|<Mn> <Et>
en-ZA|MEEEEd|<Et>, <MN>/<dN>
en-ZA|MM|<MN>
en-ZA|MMd|<MN>/<dN>
en-ZA|MME|<Et> (month: <MN>)
en-ZA|MMEd|<Et>, <MN>/<dN>
en-ZA|MMEEEE|<MN> <Et>
en-ZA|MMEEEEd|<Et>, <MN>/<dN>
en-ZA|MMM|<Mt>
en-ZA|MMMd|<dN> <Mt>
en-ZA|MMME|<Mt> <Et>
en-ZA|MMMEd|<Et>, <dN> <Mt>
en-ZA|MMMEEEE|<Mt> <Et>
en-ZA|MMMEEEEd|<Et>, <dN> <Mt>
en-ZA|MMMM|<Mt>
en-ZA|MMMMd|<dn> <Mt>
en-ZA|MMMME|<Et> (month: <Mt>)
en-ZA|MMMMEd|<Et>, <dN> <Mt>
en-ZA|MMMMEEEE|<Mt> <Et>
en-ZA|MMMMEEEEd|<Et>, <dN> <Mt>
en-ZA|y|<yn>
en-ZA|yd|<yn> (day: <dn>)
en-ZA|yE|<yn> <Et>
en-ZA|yEd|<Et> <dn> <yn>
en-ZA|yEEEE|<yn> <Et>
en-ZA|yEEEEd|<Et> <dn> <yn>
en-ZA|yM|<MN>/<yn>
en-ZA|yMd|<yn>/<MN>/<dN>
en-ZA|yME|<MN>/<yn> <Et>
en-ZA|yMEd|<Et>, <yn>/<MN>/<dN>
en-ZA|yMEEEE|<MN>/<yn> <Et>
en-ZA|yMEEEEd|<Et>, <yn>/<MN>/<dN>
en-ZA|yMM|<MN>/<yn>
en-ZA|yMMd|<yn>/<MN>/<dn>
en-ZA|yMME|<MN>/<yn> <Et>
en-ZA|yMMEd|<Et>, <yn>/<MN>/<dN>
en-ZA|yMMEEEE|<MN>/<yn> <Et>
en-ZA|yMMEEEEd|<Et>, <yn>/<MN>/<dN>
en-ZA|yMMM|<Mt> <yn>
en-ZA|yMMMd|<dN> <Mt> <yn>
en-ZA|yMMME|<Mt> <yn> <Et>
en-ZA|yMMMEd|<Et>, <dN> <Mt> <yn>
en-ZA|yMMMEEEE|<Mt> <yn> <Et>
en-ZA|yMMMEEEEd|<Et>, <dN> <Mt> <yn>
en-ZA|yMMMM|<Mt> <yn>
en-ZA|yMMMMd|<dn> <Mt> <yn>
en-ZA|yMMMME|<Mt> <yn> <Et>
en-ZA|yMMMMEd|<Et>, <dN> <Mt> <yn>
en-ZA|yMMMMEEEE|<Mt> <yn> <Et>
en-ZA|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
en-ZA|Gy|<yn> <Gt>
en-ZA|GyMMMd|<dn> <Mt> <yn> <Gt>
en-ZA|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
en-ZA|GyMd|<dN>/<MN>/<yn> <Gt>
en-ZA|s|<sn>
en-ZA|sz|<sn> <zt>
en-ZA|szzzz|<sn> <zt>
en-ZA|m|<mn>
en-ZA|mz|<mn> <zt>
en-ZA|mzzzz|<mn> <zt>
en-ZA|ms|<mN>:<sN>
en-ZA|msz|<mN>:<sN> <zt>
en-ZA|mszzzz|<mN>:<sN> <zt>
en-ZA|h|<hn> <at>
en-ZA|hz|<hn> <at> <zt>
en-ZA|hzzzz|<hn> <at> <zt>
en-ZA|hs|<hn> <at> (second: <sn>)
en-ZA|hsz|<hn> <at> <zt> (second: <sn>)
en-ZA|hszzzz|<hn> <at> <zt> (second: <sn>)
en-ZA|hm|<hn>:<mN> <at>
en-ZA|hmz|<hn>:<mN> <at> <zt>
en-ZA|hmzzzz|<hn>:<mN> <at> <zt>
en-ZA|hms|<hn>:<mN>:<sN> <at>
en-ZA|hmsz|<hn>:<mN>:<sN> <at> <zt>
en-ZA|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
en-ZA|H|<hN>
en-ZA|Hz|<hN>h <zt>
en-ZA|Hzzzz|<hN>h <zt>
en-ZA|Hs|<hN> (second: <sn>)
en-ZA|Hsz|<hN>h <zt> (second: <sn>)
en-ZA|Hszzzz|<hN>h <zt> (second: <sn>)
en-ZA|Hm|<hN>:<mN>
en-ZA|Hmz|<hN>:<mN> <zt>
en-ZA|Hmzzzz|<hN>:<mN> <zt>
en-ZA|Hms|<hN>:<mN>:<sN>
en-ZA|Hmsz|<hn>:<mN>:<sN> <zt>
en-ZA|Hmszzzz|<hn>:<mN>:<sN> <zt>
en-ZA|yMMMdh|<dN> <Mt> <yn>, <hn> <at>
en-ZA|yMMMdhm|<dN> <Mt> <yn>, <hn>:<mN> <at>
en-ZA|yMMMdHm|<dN> <Mt> <yn>, <hN>:<mN>
en-ZA|yMMMdhmz|<dN> <Mt> <yn>, <hn>:<mN> <at> <zt>
en-ZA|yMMMdHms|<dN> <Mt> <yn>, <hN>:<mN>:<sN>
en-ZA|yMMMdHmzzzz|<dN> <Mt> <yn>, <hN>:<mN> <zt>
en-ZA|MMMEdh|<Et>, <dN> <Mt>, <hn> <at>
en-ZA|MMMEdhm|<Et>, <dN> <Mt>, <hn>:<mN> <at>
en-ZA|MMMEdHm|<Et>, <dN> <Mt>, <hN>:<mN>
en-ZA|MMMEdhmz|<Et>, <dN> <Mt>, <hn>:<mN> <at> <zt>
en-ZA|MMMEdHms|<Et>, <dN> <Mt>, <hN>:<mN>:<sN>
en-ZA|MMMEdHmzzzz|<Et>, <dN> <Mt>, <hN>:<mN> <zt>
en-ZA|yMdh|<yn>/<MN>/<dN>, <hn> <at>
en-ZA|yMdhm|<yn>/<MN>/<dN>, <hn>:<mN> <at>
en-ZA|yMdHm|<yn>/<MN>/<dN>, <hN>:<mN>
en-ZA|yMdhmz|<yn>/<MN>/<dN>, <hn>:<mN> <at> <zt>
en-ZA|yMdHms|<yn>/<MN>/<dN>, <hN>:<mN>:<sN>
en-ZA|yMdHmzzzz|<yn>/<MN>/<dN>, <hN>:<mN> <zt>
en-ZA|Eh|<Et> <hn> <at>
en-ZA|Ehm|<Et> <hn>:<mN> <at>
en-ZA|EHm|<Et> <hN>:<mN>
en-ZA|Ehmz|<Et>, <hn>:<mN> <at> <zt>
en-ZA|EHms|<Et> <hN>:<mN>:<sN>
en-ZA|EHmzzzz|<Et>, <hN>:<mN> <zt>
en-ZA|dh|<dn>, <hn> <at>
en-ZA|dhm|<dn>, <hn>:<mN> <at>
en-ZA|dHm|<dn>, <hN>:<mN>
en-ZA|dhmz|<dn>, <hn>:<mN> <at> <zt>
en-ZA|dHms|<dn>, <hN>:<mN>:<sN>
en-ZA|dHmzzzz|<dn>, <hN>:<mN> <zt>
en-ZA|MMMMdh|<dn> <Mt> at <hn> <at>
en-ZA|MMMMdhm|<dn> <Mt> at <hn>:<mN> <at>
en-ZA|MMMMdHm|<dn> <Mt> at <hN>:<mN>
en-ZA|MMMMdhmz|<dn> <Mt> at <hn>:<mN> <at> <zt>
en-ZA|MMMMdHms|<dn> <Mt> at <hN>:<mN>:<sN>
en-ZA|MMMMdHmzzzz|<dn> <Mt> at <hN>:<mN> <zt>
en-ZA|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> at <hn> <at>
en-ZA|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> at <hn>:<mN> <at>
en-ZA|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> at <hN>:<mN>
en-ZA|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> at <hn>:<mN> <at> <zt>
en-ZA|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> at <hN>:<mN>:<sN>
en-ZA|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> at <hN>:<mN> <zt>
en-ZA|EEEEdh|<Et> <dn>, <hn> <at>
en-ZA|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
en-ZA|EEEEdHm|<Et> <dn>, <hN>:<mN>
en-ZA|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
en-ZA|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
en-ZA|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
US|d|<dn>
US|E|<Et>
US|Ed|<dn> <Et>
US|EEEE|<Et>
US|EEEEd|<dn> <Et>
US|M|<Mn>
US|Md|<Mn>/<dn>
US|ME|<Mn> <Et>
US|MEd|<Et>, <Mn>/<dn>
US|MEEEE|<Mn> <Et>
US|MEEEEd|<Et>, <Mn>/<dn>
US|MM|<MN>
US|MMd|<MN>/<dn>
US|MME|<Et> (month: <MN>)
US|MMEd|<Et>, <MN>/<dn>
US|MMEEEE|<MN> <Et>
US|MMEEEEd|<Et>, <MN>/<dn>
US|MMM|<Mt>
US|MMMd|<Mt> <dn>
US|MMME|<Mt> <Et>
US|MMMEd|<Et>, <Mt> <dn>
US|MMMEEEE|<Mt> <Et>
US|MMMEEEEd|<Et>, <Mt> <dn>
US|MMMM|<Mt>
US|MMMMd|<Mt> <dn>
US|MMMME|<Et> (month: <Mt>)
US|MMMMEd|<Et>, <Mt> <dn>
US|MMMMEEEE|<Mt> <Et>
US|MMMMEEEEd|<Et>, <Mt> <dn>
US|y|<yn>
US|yd|<yn> (day: <dn>)
US|yE|<yn> <Et>
US|yEd|<dn> <Et> <yn>
US|yEEEE|<yn> <Et>
US|yEEEEd|<dn> <Et> <yn>
US|yM|<Mn>/<yn>
US|yMd|<Mn>/<dn>/<yn>
US|yME|<Mn>/<yn> <Et>
US|yMEd|<Et>, <Mn>/<dn>/<yn>
US|yMEEEE|<Mn>/<yn> <Et>
US|yMEEEEd|<Et>, <Mn>/<dn>/<yn>
US|yMM|<MN>/<yn>
US|yMMd|<MN>/<dn>/<yn>
US|yMME|<MN>/<yn> <Et>
US|yMMEd|<Et>, <MN>/<dn>/<yn>
US|yMMEEEE|<MN>/<yn> <Et>
US|yMMEEEEd|<Et>, <MN>/<dn>/<yn>
US|yMMM|<Mt> <yn>
US|yMMMd|<Mt> <dn>, <yn>
US|yMMME|<Mt> <yn> <Et>
US|yMMMEd|<Et>, <Mt> <dn>, <yn>
US|yMMMEEEE|<Mt> <yn> <Et>
US|yMMMEEEEd|<Et>, <Mt> <dn>, <yn>
US|yMMMM|<Mt> <yn>
US|yMMMMd|<Mt> <dn>, <yn>
US|yMMMME|<Mt> <yn> <Et>
US|yMMMMEd|<Et>, <Mt> <dn>, <yn>
US|yMMMMEEEE|<Mt> <yn> <Et>
US|yMMMMEEEEd|<Et>, <Mt> <dn>, <yn>
US|Gy|<yn> <Gt>
US|GyMMMd|<Mt> <dn>, <yn> <Gt>
US|GyMMMMEEEEd|<Et>, <Mt> <dn>, <yn> <Gt>
US|GyMd|<Mn>/<dn>/<yn> <Gt>
US|s|<sn>
US|sz|<sn> <zt>
US|szzzz|<sn> <zt>
US|m|<mn>
US|mz|<mn> <zt>
US|mzzzz|<mn> <zt>
US|ms|<mN>:<sN>
US|msz|<mN>:<sN> <zt>
US|mszzzz|<mN>:<sN> <zt>
US|h|<hn> <at>
US|hz|<hn> <at> <zt>
US|hzzzz|<hn> <at> <zt>
US|hs|<hn> <at> (second: <sn>)
US|hsz|<hn> <at> <zt> (second: <sn>)
US|hszzzz|<hn> <at> <zt> (second: <sn>)
US|hm|<hn>:<mN> <at>
US|hmz|<hn>:<mN> <at> <zt>
US|hmzzzz|<hn>:<mN> <at> <zt>
US|hms|<hn>:<mN>:<sN> <at>
US|hmsz|<hn>:<mN>:<sN> <at> <zt>
US|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
US|H|<hN>
US|Hz|<hN>h <zt>
US|Hzzzz|<hN>h <zt>
US|Hs|<hN> (second: <sn>)
US|Hsz|<hN>h <zt> (second: <sn>)
US|Hszzzz|<hN>h <zt> (second: <sn>)
US|Hm|<hN>:<mN>
US|Hmz|<hN>:<mN> <zt>
US|Hmzzzz|<hN>:<mN> <zt>
US|Hms|<hN>:<mN>:<sN>
US|Hmsz|<hN>:<mN>:<sN> <zt>
US|Hmszzzz|<hN>:<mN>:<sN> <zt>
US|yMMMdh|<Mt> <dn>, <yn>, <hn> <at>
US|yMMMdhm|<Mt> <dn>, <yn>, <hn>:<mN> <at>
US|yMMMdHm|<Mt> <dn>, <yn>, <hN>:<mN>
US|yMMMdhmz|<Mt> <dn>, <yn>, <hn>:<mN> <at> <zt>
US|yMMMdHms|<Mt> <dn>, <yn>, <hN>:<mN>:<sN>
US|yMMMdHmzzzz|<Mt> <dn>, <yn>, <hN>:<mN> <zt>
US|MMMEdh|<Et>, <Mt> <dn>, <hn> <at>
US|MMMEdhm|<Et>, <Mt> <dn>, <hn>:<mN> <at>
US|MMMEdHm|<Et>, <Mt> <dn>, <hN>:<mN>
US|MMMEdhmz|<Et>, <Mt> <dn>, <hn>:<mN> <at> <zt>
US|MMMEdHms|<Et>, <Mt> <dn>, <hN>:<mN>:<sN>
US|MMMEdHmzzzz|<Et>, <Mt> <dn>, <hN>:<mN> <zt>
US|yMdh|<Mn>/<dn>/<yn>, <hn> <at>
US|yMdhm|<Mn>/<dn>/<yn>, <hn>:<mN> <at>
US|yMdHm|<Mn>/<dn>/<yn>, <hN>:<mN>
US|yMdhmz|<Mn>/<dn>/<yn>, <hn>:<mN> <at> <zt>
US|yMdHms|<Mn>/<dn>/<yn>, <hN>:<mN>:<sN>
US|yMdHmzzzz|<Mn>/<dn>/<yn>, <hN>:<mN> <zt>
US|Eh|<Et> <hn> <at>
US|Ehm|<Et> <hn>:<mN> <at>
US|EHm|<Et> <hN>:<mN>
US|Ehmz|<Et>, <hn>:<mN> <at> <zt>
US|EHms|<Et> <hN>:<mN>:<sN>
US|EHmzzzz|<Et>, <hN>:<mN> <zt>
US|dh|<dn>, <hn> <at>
US|dhm|<dn>, <hn>:<mN> <at>
US|dHm|<dn>, <hN>:<mN>
US|dhmz|<dn>, <hn>:<mN> <at> <zt>
US|dHms|<dn>, <hN>:<mN>:<sN>
US|dHmzzzz|<dn>, <hN>:<mN> <zt>
US|MMMMdh|<Mt> <dn> at <hn> <at>
US|MMMMdhm|<Mt> <dn> at <hn>:<mN> <at>
US|MMMMdHm|<Mt> <dn> at <hN>:<mN>
US|MMMMdhmz|<Mt> <dn> at <hn>:<mN> <at> <zt>
US|MMMMdHms|<Mt> <dn> at <hN>:<mN>:<sN>
US|MMMMdHmzzzz|<Mt> <dn> at <hN>:<mN> <zt>
US|yMMMMEEEEdh|<Et>, <Mt> <dn>, <yn> at <hn> <at>
US|yMMMMEEEEdhm|<Et>, <Mt> <dn>, <yn> at <hn>:<mN> <at>
US|yMMMMEEEEdHm|<Et>, <Mt> <dn>, <yn> at <hN>:<mN>
US|yMMMMEEEEdhmz|<Et>, <Mt> <dn>, <yn> at <hn>:<mN> <at> <zt>
US|yMMMMEEEEdHms|<Et>, <Mt> <dn>, <yn> at <hN>:<mN>:<sN>
US|yMMMMEEEEdHmzzzz|<Et>, <Mt> <dn>, <yn> at <hN>:<mN> <zt>
US|EEEEdh|<dn> <Et>, <hn> <at>
US|EEEEdhm|<dn> <Et>, <hn>:<mN> <at>
US|EEEEdHm|<dn> <Et>, <hN>:<mN>
US|EEEEdhmz|<dn> <Et>, <hn>:<mN> <at> <zt>
US|EEEEdHms|<dn> <Et>, <hN>:<mN>:<sN>
US|EEEEdHmzzzz|<dn> <Et>, <hN>:<mN> <zt>
es|d|<dn>
es|E|<Et>
es|Ed|<Et> <dn>
es|EEEE|<Et>
es|EEEEd|<Et> <dn>
es|M|<Mn>
es|Md|<dn>/<Mn>
es|ME|<Mn> <Et>
es|MEd|<Et>, <dn>/<Mn>
es|MEEEE|<Mn> <Et>
es|MEEEEd|<Et>, <dn>/<Mn>
es|MM|<MN>
es|MMd|<dn>/<Mn>
es|MME|<Et> (mes: <MN>)
es|MMEd|<Et>, <dn>/<MN>
es|MMEEEE|<MN> <Et>
es|MMEEEEd|<Et>, <dn>/<MN>
es|MMM|<Mt>
es|MMMd|<dn> <Mt>
es|MMME|<Mt> <Et>
es|MMMEd|<Et>, <dn> <Mt>
es|MMMEEEE|<Mt> <Et>
es|MMMEEEEd|<Et>, <dn> <Mt>
es|MMMM|<Mt>
es|MMMMd|<dn> de <Mt>
es|MMMME|<Et> (mes: <Mt>)
es|MMMMEd|<Et>, <dn> de <Mt>
es|MMMMEEEE|<Mt> <Et>
es|MMMMEEEEd|<Et>, <dn> de <Mt>
es|y|<yn>
es|yd|<yn> (día: <dn>)
es|yE|<yn> <Et>
es|yEd|<yn> <Et> <dn>
es|yEEEE|<yn> <Et>
es|yEEEEd|<yn> <Et> <dn>
es|yM|<Mn>/<yn>
es|yMd|<dn>/<Mn>/<yn>
es|yME|<Mn>/<yn> <Et>
es|yMEd|<Et>, <dn>/<Mn>/<yn>
es|yMEEEE|<Mn>/<yn> <Et>
es|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
es|yMM|<Mn>/<yn>
es|yMMd|<dn>/<MN>/<yn>
es|yMME|<Mn>/<yn> <Et>
es|yMMEd|<Et>, <dn>/<MN>/<yn>
es|yMMEEEE|<Mn>/<yn> <Et>
es|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
es|yMMM|<Mt> <yn>
es|yMMMd|<dn> <Mt> <yn>
es|yMMME|<Mt> <yn> <Et>
es|yMMMEd|<Et>, <dn> <Mt> <yn>
es|yMMMEEEE|<Mt> <yn> <Et>
es|yMMMEEEEd|<Et>, <dn> de <Mt> de <yn>
es|yMMMM|<Mt> de <yn>
es|yMMMMd|<dn> de <Mt> de <yn>
es|yMMMME|<Mt> de <yn> <Et>
es|yMMMMEd|<Et>, <dn> de <Mt> de <yn>
es|yMMMMEEEE|<Mt> de <yn> <Et>
es|yMMMMEEEEd|<Et>, <dn> de <Mt> de <yn>
es|Gy|<yn> <Gt>
es|GyMMMd|<dn> <Mt> <yn> <Gt>
es|GyMMMMEEEEd|<Et>, <dn> de <Mt> de <yn> <Gt>
es|GyMd|<dn>/<Mn>/<yn> <Gt>
es|s|<sn>
es|sz|<sn> <zt>
es|szzzz|<sn> <zt>
es|m|<mn>
es|mz|<mn> <zt>
es|mzzzz|<mn> <zt>
es|ms|<mN>:<sN>
es|msz|<mN>:<sN> <zt>
es|mszzzz|<mN>:<sN> <zt>
es|h|<hn> <at>
es|hz|<hn> <at> <zt>
es|hzzzz|<hn> <at> <zt>
es|hs|<hn> <at> (segundo: <sn>)
es|hsz|<hn> <at> <zt> (segundo: <sn>)
es|hszzzz|<hn> <at> <zt> (segundo: <sn>)
es|hm|<hn>:<mN> <at>
es|hmz|<hn>:<mN> <at> <zt>
es|hmzzzz|<hn>:<mN> <at> <zt>
es|hms|<hn>:<mN>:<sN> <at>
es|hmsz|<hn>:<mN>:<sN> <at> <zt>
es|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
es|H|<hn>
es|Hz|<hn> h <zt>
es|Hzzzz|<hn> h <zt>
es|Hs|<hn> (segundo: <sn>)
es|Hsz|<hn> h <zt> (segundo: <sn>)
es|Hszzzz|<hn> h <zt> (segundo: <sn>)
es|Hm|<hn>:<mN>
es|Hmz|<hn>:<mN> <zt>
es|Hmzzzz|<hn>:<mN> <zt>
es|Hms|<hn>:<mN>:<sN>
es|Hmsz|<hn>:<mN>:<sN> <zt>
es|Hmszzzz|<hn>:<mN>:<sN> (<zt>)
es|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
es|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
es|yMMMdHm|<dn> <Mt> <yn>, <hn>:<mN>
es|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
es|yMMMdHms|<dn> <Mt> <yn>, <hn>:<mN>:<sN>
es|yMMMdHmzzzz|<dn> <Mt> <yn>, <hn>:<mN> <zt>
es|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
es|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
es|MMMEdHm|<Et>, <dn> <Mt>, <hn>:<mN>
es|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
es|MMMEdHms|<Et>, <dn> <Mt>, <hn>:<mN>:<sN>
es|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hn>:<mN> <zt>
es|yMdh|<dn>/<Mn>/<yn>, <hn> <at>
es|yMdhm|<dn>/<Mn>/<yn>, <hn>:<mN> <at>
es|yMdHm|<dn>/<Mn>/<yn>, <hn>:<mN>
es|yMdhmz|<dn>/<Mn>/<yn>, <hn>:<mN> <at> <zt>
es|yMdHms|<dn>/<Mn>/<yn>, <hn>:<mN>:<sN>
es|yMdHmzzzz|<dn>/<Mn>/<yn>, <hn>:<mN> <zt>
es|Eh|<Et> <hn> <at>
es|Ehm|<Et>, <hn>:<mN> <at>
es|EHm|<Et>, <hn>:<mN>
es|Ehmz|<Et>, <hn>:<mN> <at> <zt>
es|EHms|<Et>, <hn>:<mN>:<sN>
es|EHmzzzz|<Et>, <hn>:<mN> <zt>
es|dh|<dn>, <hn> <at>
es|dhm|<dn>, <hn>:<mN> <at>
es|dHm|<dn>, <hn>:<mN>
es|dhmz|<dn>, <hn>:<mN> <at> <zt>
es|dHms|<dn>, <hn>:<mN>:<sN>
es|dHmzzzz|<dn>, <hn>:<mN> <zt>
es|MMMMdh|<dn> de <Mt> a las <hn> <at>
es|MMMMdhm|<dn> de <Mt> a las <hn>:<mN> <at>
es|MMMMdHm|<dn> de <Mt> a las <hn>:<mN>
es|MMMMdhmz|<dn> de <Mt> a las <hn>:<mN> <at> <zt>
es|MMMMdHms|<dn> de <Mt> a las <hn>:<mN>:<sN>
es|MMMMdHmzzzz|<dn> de <Mt> a las <hn>:<mN> <zt>
es|yMMMMEEEEdh|<Et>, <dn> de <Mt> de <yn>, <hn> <at>
es|yMMMMEEEEdhm|<Et>, <dn> de <Mt> de <yn>, <hn>:<mN> <at>
es|yMMMMEEEEdHm|<Et>, <dn> de <Mt> de <yn>, <hn>:<mN>
es|yMMMMEEEEdhmz|<Et>, <dn> de <Mt> de <yn>, <hn>:<mN> <at> <zt>
es|yMMMMEEEEdHms|<Et>, <dn> de <Mt> de <yn>, <hn>:<mN>:<sN>
es|yMMMMEEEEdHmzzzz|<Et>, <dn> de <Mt> de <yn>, <hn>:<mN> <zt>
es|EEEEdh|<Et> <dn>, <hn> <at>
es|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
es|EEEEdHm|<Et> <dn>, <hn>:<mN>
es|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
es|EEEEdHms|<Et> <dn>, <hn>:<mN>:<sN>
es|EEEEdHmzzzz|<Et> <dn>, <hn>:<mN> <zt>
et|d|<dn>
et|E|<Et>
et|Ed|<Et>, <dn>
et|EEEE|<Et>
et|EEEEd|<Et>, <dn>
et|M|<Mn>
et|Md|<dn>.<Mn>
et|ME|<Mn> <Et>
et|MEd|<Et>, <dn>.<Mn>
et|MEEEE|<Mn> <Et>
et|MEEEEd|<Et>, <dn>.<Mn>
et|MM|<MN>
et|MMd|<dn>.<MN>
et|MME|<Et> (kuu: <MN>)
et|MMEd|<Et>, <dn>.<MN>
et|MMEEEE|<MN> <Et>
et|MMEEEEd|<Et>, <dn>.<MN>
et|MMM|<Mt>
et|MMMd|<dn>. <Mt>
et|MMME|<Mt> <Et>
et|MMMEd|<Et>, <dn>. <Mt>
et|MMMEEEE|<Mt> <Et>
et|MMMEEEEd|<Et>, <dn>. <Mt>
et|MMMM|<Mt>
et|MMMMd|<dn>. <Mt>
et|MMMME|<Et> (kuu: <Mt>)
et|MMMMEd|<Et>, <dn>. <Mt>
et|MMMMEEEE|<Mt> <Et>
et|MMMMEEEEd|<Et>, <dn>. <Mt>
et|y|<yn>
et|yd|<yn> (päev: <dn>)
et|yE|<yn> <Et>
et|yEd|<yn> <Et>, <dn>
et|yEEEE|<yn> <Et>
et|yEEEEd|<yn> <Et>, <dn>
et|yM|<Mn>.<yn>
et|yMd|<dn>.<Mn>.<yn>
et|yME|<Mn>.<yn> <Et>
et|yMEd|<Et>, <dn>.<Mn>.<yn>
et|yMEEEE|<Mn>.<yn> <Et>
et|yMEEEEd|<Et>, <dn>.<Mn>.<yn>
et|yMM|<MN>.<yn>
et|yMMd|<dn>.<MN>.<yn>
et|yMME|<MN>.<yn> <Et>
et|yMMEd|<Et>, <dn>.<MN>.<yn>
et|yMMEEEE|<MN>.<yn> <Et>
et|yMMEEEEd|<Et>, <dn>.<MN>.<yn>
et|yMMM|<Mt> <yn>
et|yMMMd|<dn>. <Mt> <yn>
et|yMMME|<Mt> <yn> <Et>
et|yMMMEd|<Et>, <dn>. <Mt> <yn>
et|yMMMEEEE|<Mt> <yn> <Et>
et|yMMMEEEEd|<Et>, <dn>. <Mt> <yn>
et|yMMMM|<Mt> <yn>
et|yMMMMd|<dn>. <Mt> <yn>
et|yMMMME|<Mt> <yn> <Et>
et|yMMMMEd|<Et>, <dn>. <Mt> <yn>
et|yMMMMEEEE|<Mt> <yn> <Et>
et|yMMMMEEEEd|<Et>, <dn>. <Mt> <yn>
et|Gy|<yn> <Gt>
et|GyMMMd|<dn>. <Mt> <yn> <Gt>
et|GyMMMMEEEEd|<Et>, <dn>. <Mt> <yn> <Gt>
et|GyMd|<dn>.<MN>.<yn> <Gt>
et|s|<sn>
et|sz|<sn> <zt>
et|szzzz|<sn> <zt>
et|m|<mn>
et|mz|<mn> <zt>
et|mzzzz|<mn> <zt>
et|ms|<mN>:<sN>
et|msz|<mN>:<sN> <zt>
et|mszzzz|<mN>:<sN> <zt>
et|h|<hn> <at>
et|hz|<hn> <at> <zt>
et|hzzzz|<hn> <at> <zt>
et|hs|<hn> <at> (sekund: <sn>)
et|hsz|<hn> <at> <zt> (sekund: <sn>)
et|hszzzz|<hn> <at> <zt> (sekund: <sn>)
et|hm|<hn>:<mN> <at>
et|hmz|<hn>:<mN> <at> <zt>
et|hmzzzz|<hn>:<mN> <at> <zt>
et|hms|<hn>:<mN>:<sN> <at>
et|hmsz|<hn>:<mN>:<sN> <at> <zt>
et|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
et|H|<hN>
et|Hz|<hN>h <zt>
et|Hzzzz|<hN>h <zt>
et|Hs|<hN> (sekund: <sn>)
et|Hsz|<hN>h <zt> (sekund: <sn>)
et|Hszzzz|<hN>h <zt> (sekund: <sn>)
et|Hm|<hN>:<mN>
et|Hmz|<hN>:<mN> <zt>
et|Hmzzzz|<hN>:<mN> <zt>
et|Hms|<hN>:<mN>:<sN>
et|Hmsz|<hn>:<mN>:<sN> <zt>
et|Hmszzzz|<hn>:<mN>:<sN> <zt>
et|yMMMdh|<dn>. <Mt> <yn>, <hn> <at>
et|yMMMdhm|<dn>. <Mt> <yn>, <hn>:<mN> <at>
et|yMMMdHm|<dn>. <Mt> <yn>, <hN>:<mN>
et|yMMMdhmz|<dn>. <Mt> <yn>, <hn>:<mN> <at> <zt>
et|yMMMdHms|<dn>. <Mt> <yn>, <hN>:<mN>:<sN>
et|yMMMdHmzzzz|<dn>. <Mt> <yn>, <hN>:<mN> <zt>
et|MMMEdh|<Et>, <dn>. <Mt>, <hn> <at>
et|MMMEdhm|<Et>, <dn>. <Mt>, <hn>:<mN> <at>
et|MMMEdHm|<Et>, <dn>. <Mt>, <hN>:<mN>
et|MMMEdhmz|<Et>, <dn>. <Mt>, <hn>:<mN> <at> <zt>
et|MMMEdHms|<Et>, <dn>. <Mt>, <hN>:<mN>:<sN>
et|MMMEdHmzzzz|<Et>, <dn>. <Mt>, <hN>:<mN> <zt>
et|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
et|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
et|yMdHm|<dn>.<Mn>.<yn>, <hN>:<mN>
et|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at> <zt>
et|yMdHms|<dn>.<Mn>.<yn>, <hN>:<mN>:<sN>
et|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>:<mN> <zt>
et|Eh|<Et> <hn> <at>
et|Ehm|<Et> <hn>:<mN> <at>
et|EHm|<Et> <hN>:<mN>
et|Ehmz|<Et>, <hn>:<mN> <at> <zt>
et|EHms|<Et> <hN>:<mN>:<sN>
et|EHmzzzz|<Et>, <hN>:<mN> <zt>
et|dh|<dn>, <hn> <at>
et|dhm|<dn>, <hn>:<mN> <at>
et|dHm|<dn>, <hN>:<mN>
et|dhmz|<dn>, <hn>:<mN> <at> <zt>
et|dHms|<dn>, <hN>:<mN>:<sN>
et|dHmzzzz|<dn>, <hN>:<mN> <zt>
et|MMMMdh|<dn>. <Mt>, kell <hn> <at>
et|MMMMdhm|<dn>. <Mt>, kell <hn>:<mN> <at>
et|MMMMdHm|<dn>. <Mt>, kell <hN>:<mN>
et|MMMMdhmz|<dn>. <Mt>, kell <hn>:<mN> <at> <zt>
et|MMMMdHms|<dn>. <Mt>, kell <hN>:<mN>:<sN>
et|MMMMdHmzzzz|<dn>. <Mt>, kell <hN>:<mN> <zt>
et|yMMMMEEEEdh|<Et>, <dn>. <Mt> <yn>, kell <hn> <at>
et|yMMMMEEEEdhm|<Et>, <dn>. <Mt> <yn>, kell <hn>:<mN> <at>
et|yMMMMEEEEdHm|<Et>, <dn>. <Mt> <yn>, kell <hN>:<mN>
et|yMMMMEEEEdhmz|<Et>, <dn>. <Mt> <yn>, kell <hn>:<mN> <at> <zt>
et|yMMMMEEEEdHms|<Et>, <dn>. <Mt> <yn>, kell <hN>:<mN>:<sN>
et|yMMMMEEEEdHmzzzz|<Et>, <dn>. <Mt> <yn>, kell <hN>:<mN> <zt>
et|EEEEdh|<Et>, <dn>, <hn> <at>
et|EEEEdhm|<Et>, <dn>, <hn>:<mN> <at>
et|EEEEdHm|<Et>, <dn>, <hN>:<mN>
et|EEEEdhmz|<Et>, <dn>, <hn>:<mN> <at> <zt>
et|EEEEdHms|<Et>, <dn>, <hN>:<mN>:<sN>
et|EEEEdHmzzzz|<Et>, <dn>, <hN>:<mN> <zt>
eu|d|<dn>
eu|E|<Et>
eu|Ed|<dn>, <Et>
eu|EEEE|<Et>
eu|EEEEd|<dn>, <Et>
eu|M|<Mn>
eu|Md|<Mn>/<dn>
eu|ME|<Mn> <Et>
eu|MEd|<Mn>/<dn>, <Et>
eu|MEEEE|<Mn> <Et>
eu|MEEEEd|<Mn>/<dn>, <Et>
eu|MM|<MN>
eu|MMd|<MN>/<dn>
eu|MME|<Et> (hilabetea: <MN>)
eu|MMEd|<MN>/<dn>, <Et>
eu|MMEEEE|<MN> <Et>
eu|MMEEEEd|<MN>/<dn>, <Et>
eu|MMM|<Mt>
eu|MMMd|<Mt> <dn>(a)
eu|MMME|<Mt> <Et>
eu|MMMEd|<Mt> <dn>(a), <Et>
eu|MMMEEEE|<Mt> <Et>
eu|MMMEEEEd|<Mt> <dn>(a), <Et>
eu|MMMM|<Mt>
eu|MMMMd|<Mt>ren <dn>(a)
eu|MMMME|<Et> (hilabetea: <Mt>)
eu|MMMMEd|<Mt> <dn>(a), <Et>
eu|MMMMEEEE|<Mt> <Et>
eu|MMMMEEEEd|<Mt> <dn>(a), <Et>
eu|y|<yn>
eu|yd|<yn> (eguna: <dn>)
eu|yE|<yn> <Et>
eu|yEd|<yn> <dn>, <Et>
eu|yEEEE|<yn> <Et>
eu|yEEEEd|<yn> <dn>, <Et>
eu|yM|<yn>/<Mn>
eu|yMd|<yn>/<Mn>/<dn>
eu|yME|<yn>/<Mn> <Et>
eu|yMEd|<yn>/<Mn>/<dn>, <Et>
eu|yMEEEE|<yn>/<Mn> <Et>
eu|yMEEEEd|<yn>/<Mn>/<dn>, <Et>
eu|yMM|<yn>/<MN>
eu|yMMd|<yn>/<MN>/<dn>
eu|yMME|<yn>/<MN> <Et>
eu|yMMEd|<yn>/<MN>/<dn>, <Et>
eu|yMMEEEE|<yn>/<MN> <Et>
eu|yMMEEEEd|<yn>/<MN>/<dn>, <Et>
eu|yMMM|<yn> <Mt>
eu|yMMMd|<yn> <Mt> <dn>(a)
eu|yMMME|<yn> <Mt> <Et>
eu|yMMMEd|<yn> <Mt> <dn>(a), <Et>
eu|yMMMEEEE|<yn> <Mt> <Et>
eu|yMMMEEEEd|<yn>(e)ko <Mt>ren <dn>(a), <Et>
eu|yMMMM|<yn>(e)ko <Mt>
eu|yMMMMd|<yn>(e)ko <Mt>ren <dn>(a)
eu|yMMMME|<yn>(e)ko <Mt> <Et>
eu|yMMMMEd|<yn>(e)ko <Mt>ren <dn>(a), <Et>
eu|yMMMMEEEE|<yn>(e)ko <Mt> <Et>
eu|yMMMMEEEEd|<yn>(e)ko <Mt>ren <dn>(a), <Et>
eu|Gy|<Gt> <yn>
eu|GyMMMd|<Gt> <yn>. urteko <Mt> <dn>(a)
eu|GyMMMMEEEEd|<Gt> <yn>. urteko <Mt> <dn>(a), <Et>
eu|GyMd|<Gt> <yn>-<MN>-<dN>
eu|s|<sn>
eu|sz|<sn> (<zt>)
eu|szzzz|<sn> (<zt>)
eu|m|<mn>
eu|mz|<mn> (<zt>)
eu|mzzzz|<mn> (<zt>)
eu|ms|<mN>:<sN>
eu|msz|<mN>:<sN> (<zt>)
eu|mszzzz|<mN>:<sN> (<zt>)
eu|h|<hn> <at>
eu|hz|<hn> <at> <zt>
eu|hzzzz|<hn> <at> <zt>
eu|hs|<hn> <at> (segundoa: <sn>)
eu|hsz|<hn> <at> <zt> (segundoa: <sn>)
eu|hszzzz|<hn> <at> <zt> (segundoa: <sn>)
eu|hm|<hn>:<mN> <at>
eu|hmz|<hn>:<mN> <at> <zt>
eu|hmzzzz|<hn>:<mN> <at> <zt>
eu|hms|<hn>:<mN>:<sN> <at>
eu|hmsz|<hn>:<mN>:<sN> <at> <zt>
eu|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
eu|H|<hN>
eu|Hz|<hN>h <zt>
eu|Hzzzz|<hN>h <zt>
eu|Hs|<hN> (segundoa: <sn>)
eu|Hsz|<hN>h <zt> (segundoa: <sn>)
eu|Hszzzz|<hN>h <zt> (segundoa: <sn>)
eu|Hm|<hN>:<mN>
eu|Hmz|<hN>:<mN> <zt>
eu|Hmzzzz|<hN>:<mN> <zt>
eu|Hms|<hN>:<mN>:<sN>
eu|Hmsz|<hn>:<mN>:<sN> (<zt>)
eu|Hmszzzz|<hn>:<mN>:<sN> (<zt>)
eu|yMMMdh|<yn> <Mt> <dn>(a) (<hn> <at>)
eu|yMMMdhm|<yn> <Mt> <dn>(a) (<hn>:<mN> <at>)
eu|yMMMdHm|<yn> <Mt> <dn>(a) (<hN>:<mN>)
eu|yMMMdhmz|<yn> <Mt> <dn>(a) (<hn>:<mN> <at> <zt>)
eu|yMMMdHms|<yn> <Mt> <dn>(a) (<hN>:<mN>:<sN>)
eu|yMMMdHmzzzz|<yn> <Mt> <dn>(a) (<hN>:<mN> <zt>)
eu|MMMEdh|<Mt> <dn>(a), <Et> (<hn> <at>)
eu|MMMEdhm|<Mt> <dn>(a), <Et> (<hn>:<mN> <at>)
eu|MMMEdHm|<Mt> <dn>(a), <Et> (<hN>:<mN>)
eu|MMMEdhmz|<Mt> <dn>(a), <Et> (<hn>:<mN> <at> <zt>)
eu|MMMEdHms|<Mt> <dn>(a), <Et> (<hN>:<mN>:<sN>)
eu|MMMEdHmzzzz|<Mt> <dn>(a), <Et> (<hN>:<mN> <zt>)
eu|yMdh|<yn>/<Mn>/<dn> (<hn> <at>)
eu|yMdhm|<yn>/<Mn>/<dn> (<hn>:<mN> <at>)
eu|yMdHm|<yn>/<Mn>/<dn> (<hN>:<mN>)
eu|yMdhmz|<yn>/<Mn>/<dn> (<hn>:<mN> <at> <zt>)
eu|yMdHms|<yn>/<Mn>/<dn> (<hN>:<mN>:<sN>)
eu|yMdHmzzzz|<yn>/<Mn>/<dn> (<hN>:<mN> <zt>)
eu|Eh|<Et> <hn> <at>
eu|Ehm|<Et> <hn>:<mN> <at>
eu|EHm|<Et> <hN>:<mN>
eu|Ehmz|<Et> (<hn>:<mN> <at> <zt>)
eu|EHms|<Et> <hN>:<mN>:<sN>
eu|EHmzzzz|<Et> (<hN>:<mN> <zt>)
eu|dh|<dn> (<hn> <at>)
eu|dhm|<dn> (<hn>:<mN> <at>)
eu|dHm|<dn> (<hN>:<mN>)
eu|dhmz|<dn> (<hn>:<mN> <at> <zt>)
eu|dHms|<dn> (<hN>:<mN>:<sN>)
eu|dHmzzzz|<dn> (<hN>:<mN> <zt>)
eu|MMMMdh|<Mt>ren <dn>(a) (<hn> <at>)
eu|MMMMdhm|<Mt>ren <dn>(a) (<hn>:<mN> <at>)
eu|MMMMdHm|<Mt>ren <dn>(a) (<hN>:<mN>)
eu|MMMMdhmz|<Mt>ren <dn>(a) (<hn>:<mN> <at> <zt>)
eu|MMMMdHms|<Mt>ren <dn>(a) (<hN>:<mN>:<sN>)
eu|MMMMdHmzzzz|<Mt>ren <dn>(a) (<hN>:<mN> <zt>)
eu|yMMMMEEEEdh|<yn>(e)ko <Mt>ren <dn>(a), <Et> (<hn> <at>)
eu|yMMMMEEEEdhm|<yn>(e)ko <Mt>ren <dn>(a), <Et> (<hn>:<mN> <at>)
eu|yMMMMEEEEdHm|<yn>(e)ko <Mt>ren <dn>(a), <Et> (<hN>:<mN>)
eu|yMMMMEEEEdhmz|<yn>(e)ko <Mt>ren <dn>(a), <Et> (<hn>:<mN> <at> <zt>)
eu|yMMMMEEEEdHms|<yn>(e)ko <Mt>ren <dn>(a), <Et> (<hN>:<mN>:<sN>)
eu|yMMMMEEEEdHmzzzz|<yn>(e)ko <Mt>ren <dn>(a), <Et> (<hN>:<mN> <zt>)
eu|EEEEdh|<dn>, <Et> (<hn> <at>)
eu|EEEEdhm|<dn>, <Et> (<hn>:<mN> <at>)
eu|EEEEdHm|<dn>, <Et> (<hN>:<mN>)
eu|EEEEdhmz|<dn>, <Et> (<hn>:<mN> <at> <zt>)
eu|EEEEdHms|<dn>, <Et> (<hN>:<mN>:<sN>)
eu|EEEEdHmzzzz|<dn>, <Et> (<hN>:<mN> <zt>)
fa|d|<dn>
fa|E|<Et>
fa|Ed|<Et> <dn>
fa|EEEE|<Et>
fa|EEEEd|<Et> <dn>
fa|M|<Mn>
fa|Md|<Mn>/<dn>
fa|ME|<Et> <Mn>
fa|MEd|<Et> <Mn>/<dn>
fa|MEEEE|<Et> <Mn>
fa|MEEEEd|<Et> <Mn>/<dn>
fa|MM|<MN>
fa|MMd|<MN>/<dn>
fa|MME|<Et> (ماه: <MN>)
fa|MMEd|<Et> <MN>/<dn>
fa|MMEEEE|<Et> <MN>
fa|MMEEEEd|<Et> <MN>/<dn>
fa|MMM|<Mt>
fa|MMMd|<dn> <Mt>
fa|MMME|<Et> <Mt>
fa|MMMEd|<Et> <dn> <Mt>
fa|MMMEEEE|<Et> <Mt>
fa|MMMEEEEd|<Et> <dn> <Mt>
fa|MMMM|<Mt>
fa|MMMMd|<dn> <Mt>
fa|MMMME|<Et> (ماه: <Mt>)
fa|MMMMEd|<Et> <dn> <Mt>
fa|MMMMEEEE|<Et> <Mt>
fa|MMMMEEEEd|<Et> <dn> <Mt>
fa|y|<yn>
fa|yd|<yn> (روز: <dn>)
fa|yE|<Et> <yn>
fa|yEd|<yn> <Et> <dn>
fa|yEEEE|<Et> <yn>
fa|yEEEEd|<yn> <Et> <dn>
fa|yM|<yn>/<Mn>
fa|yMd|<yn>/<Mn>/<dn>
fa|yME|<Et> <yn>/<Mn>
fa|yMEd|<Et> <yn>/<Mn>/<dn>
fa|yMEEEE|<Et> <yn>/<Mn>
fa|yMEEEEd|<Et> <yn>/<Mn>/<dn>
fa|yMM|<yn>/<MN>
fa|yMMd|<yn>/<MN>/<dn>
fa|yMME|<Et> <yn>/<MN>
fa|yMMEd|<Et> <yn>/<MN>/<dn>
fa|yMMEEEE|<Et> <yn>/<MN>
fa|yMMEEEEd|<Et> <yn>/<MN>/<dn>
fa|yMMM|<Mt> <yn>
fa|yMMMd|<dn> <Mt> <yn>
fa|yMMME|<Et> <Mt> <yn>
fa|yMMMEd|<Et> <dn> <Mt> <yn>
fa|yMMMEEEE|<Et> <Mt> <yn>
fa|yMMMEEEEd|<Et> <dn> <Mt> <yn>
fa|yMMMM|<Mt> <yn>
fa|yMMMMd|<dn> <Mt> <yn>
fa|yMMMME|<Et> <Mt> <yn>
fa|yMMMMEd|<Et> <dn> <Mt> <yn>
fa|yMMMMEEEE|<Et> <Mt> <yn>
fa|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
fa|Gy|<yn> <Gt>
fa|GyMMMd|<dn> <Mt> <yn> <Gt>
fa|GyMMMMEEEEd|<Et> <dn> <Mt> <yn> <Gt>
fa|GyMd|<yn>/<Mn>/<dn> <Gt>
fa|s|<sn>
fa|sz|<sn> (<zt>)
fa|szzzz|<sn> (<zt>)
fa|m|<mn>
fa|mz|<mn> (<zt>)
fa|mzzzz|<mn> (<zt>)
fa|ms|<mn>:<sN>
fa|msz|<mn>:<sN> (<zt>)
fa|mszzzz|<mn>:<sN> (<zt>)
fa|h|<hn> <at>
fa|hz|<hn> <at> <zt>
fa|hzzzz|<hn> <at> <zt>
fa|hs|<hn> <at> (ثانیه: <sn>)
fa|hsz|<hn> <at> <zt> (ثانیه: <sn>)
fa|hszzzz|<hn> <at> <zt> (ثانیه: <sn>)
fa|hm|<hn>:<mN> <at>
fa|hmz|<hn>:<mN> <at> <zt>
fa|hmzzzz|<hn>:<mN> <at> <zt>
fa|hms|<hn>:<mN>:<sN> <at>
fa|hmsz|<hn>:<mN>:<sN> <at> <zt>
fa|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
fa|H|<hn>
fa|Hz|س<hN> <zt>
fa|Hzzzz|س<hN> <zt>
fa|Hs|<hn> (ثانیه: <sn>)
fa|Hsz|س<hN> <zt> (ثانیه: <sn>)
fa|Hszzzz|س<hN> <zt> (ثانیه: <sn>)
fa|Hm|<hn>:<mN>
fa|Hmz|<hn>:<mN> (<zt>)
fa|Hmzzzz|<hn>:<mN> (<zt>)
fa|Hms|<hn>:<mN>:<sN>
fa|Hmsz|<hn>:<mN>:<sN> (<zt>)
fa|Hmszzzz|<hn>:<mN>:<sN> (<zt>)
fa|yMMMdh|<dn> <Mt> <yn>، <hn> <at>
fa|yMMMdhm|<dn> <Mt> <yn>، <hn>:<mN> <at>
fa|yMMMdHm|<dn> <Mt> <yn>، <hn>:<mN>
fa|yMMMdhmz|<dn> <Mt> <yn>، <hn>:<mN> <at> <zt>
fa|yMMMdHms|<dn> <Mt> <yn>، <hn>:<mN>:<sN>
fa|yMMMdHmzzzz|<dn> <Mt> <yn>، <hn>:<mN> (<zt>)
fa|MMMEdh|<Et> <dn> <Mt>، <hn> <at>
fa|MMMEdhm|<Et> <dn> <Mt>، <hn>:<mN> <at>
fa|MMMEdHm|<Et> <dn> <Mt>، <hn>:<mN>
fa|MMMEdhmz|<Et> <dn> <Mt>، <hn>:<mN> <at> <zt>
fa|MMMEdHms|<Et> <dn> <Mt>، <hn>:<mN>:<sN>
fa|MMMEdHmzzzz|<Et> <dn> <Mt>، <hn>:<mN> (<zt>)
fa|yMdh|<yn>/<Mn>/<dn>, <hn> <at>
fa|yMdhm|<yn>/<Mn>/<dn>, <hn>:<mN> <at>
fa|yMdHm|<yn>/<Mn>/<dn>, <hn>:<mN>
fa|yMdhmz|<yn>/<Mn>/<dn>, <hn>:<mN> <at> <zt>
fa|yMdHms|<yn>/<Mn>/<dn>, <hn>:<mN>:<sN>
fa|yMdHmzzzz|<yn>/<Mn>/<dn>, <hn>:<mN> (<zt>)
fa|Eh|<Et> <hn> <at>
fa|Ehm|<Et> <hn>:<mN> <at>
fa|EHm|<Et> <hn>:<mN>
fa|Ehmz|<Et>, <hn>:<mN> <at> <zt>
fa|EHms|<Et> <hn>:<mN>:<sN>
fa|EHmzzzz|<Et>, <hn>:<mN> (<zt>)
fa|dh|<dn>, <hn> <at>
fa|dhm|<dn>, <hn>:<mN> <at>
fa|dHm|<dn>, <hn>:<mN>
fa|dhmz|<dn>, <hn>:<mN> <at> <zt>
fa|dHms|<dn>, <hn>:<mN>:<sN>
fa|dHmzzzz|<dn>, <hn>:<mN> (<zt>)
fa|MMMMdh|<dn> <Mt> ساعت <hn> <at>
fa|MMMMdhm|<dn> <Mt> ساعت <hn>:<mN> <at>
fa|MMMMdHm|<dn> <Mt> ساعت <hn>:<mN>
fa|MMMMdhmz|<dn> <Mt> ساعت <hn>:<mN> <at> <zt>
fa|MMMMdHms|<dn> <Mt> ساعت <hn>:<mN>:<sN>
fa|MMMMdHmzzzz|<dn> <Mt> ساعت <hn>:<mN> (<zt>)
fa|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> ساعت <hn> <at>
fa|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> ساعت <hn>:<mN> <at>
fa|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> ساعت <hn>:<mN>
fa|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> ساعت <hn>:<mN> <at> <zt>
fa|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> ساعت <hn>:<mN>:<sN>
fa|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> ساعت <hn>:<mN> (<zt>)
fa|EEEEdh|<Et> <dn>, <hn> <at>
fa|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
fa|EEEEdHm|<Et> <dn>, <hn>:<mN>
fa|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
fa|EEEEdHms|<Et> <dn>, <hn>:<mN>:<sN>
fa|EEEEdHmzzzz|<Et> <dn>, <hn>:<mN> (<zt>)
fi|d|<dn>
fi|E|<Et>
fi|Ed|<Et> <dn>.
fi|EEEE|<Et>
fi|EEEEd|<Et> <dn>.
fi|M|<Mn>
fi|Md|<dn>.<Mn>.
fi|ME|<Mn> <Et>
fi|MEd|<Et> <dn>.<Mn>.
fi|MEEEE|<Mn> <Et>
fi|MEEEEd|<Et> <dn>.<Mn>.
fi|MM|<MN>
fi|MMd|<dn>.<MN>.
fi|MME|<Et> (kuukausi: <MN>)
fi|MMEd|<Et> <dn>.<MN>.
fi|MMEEEE|<MN> <Et>
fi|MMEEEEd|<Et> <dn>.<MN>.
fi|MMM|<Mt>
fi|MMMd|<dn>.<Mn>.
fi|MMME|<Mt> <Et>
fi|MMMEd|<Et> <dn>.<Mn>.
fi|MMMEEEE|<Mt> <Et>
fi|MMMEEEEd|<Et> <dn>.<Mn>.
fi|MMMM|<Mt>
fi|MMMMd|<dn>. <Mt>
fi|MMMME|<Et> (kuukausi: <Mt>)
fi|MMMMEd|<Et> <dn>. <Mt>
fi|MMMMEEEE|<Mt> <Et>
fi|MMMMEEEEd|<Et> <dn>. <Mt>
fi|y|<yn>
fi|yd|<yn> (päivä: <dn>)
fi|yE|<yn> <Et>
fi|yEd|<yn> <Et> <dn>.
fi|yEEEE|<yn> <Et>
fi|yEEEEd|<yn> <Et> <dn>.
fi|yM|<Mn>.<yn>
fi|yMd|<dn>.<Mn>.<yn>
fi|yME|<Mn>.<yn> <Et>
fi|yMEd|<Et> <dn>.<Mn>.<yn>
fi|yMEEEE|<Mn>.<yn> <Et>
fi|yMEEEEd|<Et> <dn>.<Mn>.<yn>
fi|yMM|<Mn>.<yn>
fi|yMMd|<dn>.<MN>.<yn>
fi|yMME|<Mn>.<yn> <Et>
fi|yMMEd|<Et> <dn>.<MN>.<yn>
fi|yMMEEEE|<Mn>.<yn> <Et>
fi|yMMEEEEd|<Et> <dn>.<MN>.<yn>
fi|yMMM|<Mt> <yn>
fi|yMMMd|<dn>.<Mn>.<yn>
fi|yMMME|<Mt> <yn> <Et>
fi|yMMMEd|<Et> <dn>.<Mn>.<yn>
fi|yMMMEEEE|<Mt> <yn> <Et>
fi|yMMMEEEEd|<Et> <dn>.<Mn>.<yn>
fi|yMMMM|<Mt> <yn>
fi|yMMMMd|<dn>. <Mt> <yn>
fi|yMMMME|<Mt> <yn> <Et>
fi|yMMMMEd|<Et> <dn>. <Mt> <yn>
fi|yMMMMEEEE|<Mt> <yn> <Et>
fi|yMMMMEEEEd|<Et> <dn>. <Mt> <yn>
fi|Gy|<yn> <Gt>
fi|GyMMMd|<dn>.<Mn>.<yn> <Gt>
fi|GyMMMMEEEEd|<Et> <dn>. <Mt> <yn> <Gt>
fi|GyMd|<Mn>.<dn>.<yn> <Gt>
fi|s|<sn>
fi|sz|<sn> <zt>
fi|szzzz|<sn> <zt>
fi|m|<mn>
fi|mz|<mn> <zt>
fi|mzzzz|<mn> <zt>
fi|ms|<mn>.<sN>
fi|msz|<mn>.<sN> <zt>
fi|mszzzz|<mn>.<sN> <zt>
fi|h|<hn> <at>
fi|hz|<hn> <at> <zt>
fi|hzzzz|<hn> <at> <zt>
fi|hs|<hn> <at> (sekunti: <sn>)
fi|hsz|<hn> <at> <zt> (sekunti: <sn>)
fi|hszzzz|<hn> <at> <zt> (sekunti: <sn>)
fi|hm|<hn>.<mN> <at>
fi|hmz|<hn>.<mN> <at> <zt>
fi|hmzzzz|<hn>.<mN> <at> <zt>
fi|hms|<hn>.<mN>.<sN> <at>
fi|hmsz|<hn>.<mN>.<sN> <at> <zt>
fi|hmszzzz|<hn>.<mN>.<sN> <at> <zt>
fi|H|<hn>
fi|Hz|<hn> h <zt>
fi|Hzzzz|<hn> h <zt>
fi|Hs|<hn> (sekunti: <sn>)
fi|Hsz|<hn> h <zt> (sekunti: <sn>)
fi|Hszzzz|<hn> h <zt> (sekunti: <sn>)
fi|Hm|<hn>.<mN>
fi|Hmz|<hn>.<mN> <zt>
fi|Hmzzzz|<hn>.<mN> <zt>
fi|Hms|<hn>.<mN>.<sN>
fi|Hmsz|<hn>.<mN>.<sN> <zt>
fi|Hmszzzz|<hn>.<mN>.<sN> <zt>
fi|yMMMdh|<dn>.<Mn>.<yn> klo <hn> <at>
fi|yMMMdhm|<dn>.<Mn>.<yn> klo <hn>.<mN> <at>
fi|yMMMdHm|<dn>.<Mn>.<yn> klo <hn>.<mN>
fi|yMMMdhmz|<dn>.<Mn>.<yn> klo <hn>.<mN> <at> <zt>
fi|yMMMdHms|<dn>.<Mn>.<yn> klo <hn>.<mN>.<sN>
fi|yMMMdHmzzzz|<dn>.<Mn>.<yn> klo <hn>.<mN> <zt>
fi|MMMEdh|<Et> <dn>.<Mn>. klo <hn> <at>
fi|MMMEdhm|<Et> <dn>.<Mn>. klo <hn>.<mN> <at>
fi|MMMEdHm|<Et> <dn>.<Mn>. klo <hn>.<mN>
fi|MMMEdhmz|<Et> <dn>.<Mn>. klo <hn>.<mN> <at> <zt>
fi|MMMEdHms|<Et> <dn>.<Mn>. klo <hn>.<mN>.<sN>
fi|MMMEdHmzzzz|<Et> <dn>.<Mn>. klo <hn>.<mN> <zt>
fi|yMdh|<dn>.<Mn>.<yn> klo <hn> <at>
fi|yMdhm|<dn>.<Mn>.<yn> klo <hn>.<mN> <at>
fi|yMdHm|<dn>.<Mn>.<yn> klo <hn>.<mN>
fi|yMdhmz|<dn>.<Mn>.<yn> klo <hn>.<mN> <at> <zt>
fi|yMdHms|<dn>.<Mn>.<yn> klo <hn>.<mN>.<sN>
fi|yMdHmzzzz|<dn>.<Mn>.<yn> klo <hn>.<mN> <zt>
fi|Eh|<Et> <hn> <at>
fi|Ehm|<Et> <hn>.<mN> <at>
fi|EHm|<Et> <hn>.<mN>
fi|Ehmz|<Et> klo <hn>.<mN> <at> <zt>
fi|EHms|<Et> <hn>.<mN>.<sN>
fi|EHmzzzz|<Et> klo <hn>.<mN> <zt>
fi|dh|<dn> klo <hn> <at>
fi|dhm|<dn> klo <hn>.<mN> <at>
fi|dHm|<dn> klo <hn>.<mN>
fi|dhmz|<dn> klo <hn>.<mN> <at> <zt>
fi|dHms|<dn> klo <hn>.<mN>.<sN>
fi|dHmzzzz|<dn> klo <hn>.<mN> <zt>
fi|MMMMdh|<dn>. <Mt> klo <hn> <at>
fi|MMMMdhm|<dn>. <Mt> klo <hn>.<mN> <at>
fi|MMMMdHm|<dn>. <Mt> klo <hn>.<mN>
fi|MMMMdhmz|<dn>. <Mt> klo <hn>.<mN> <at> <zt>
fi|MMMMdHms|<dn>. <Mt> klo <hn>.<mN>.<sN>
fi|MMMMdHmzzzz|<dn>. <Mt> klo <hn>.<mN> <zt>
fi|yMMMMEEEEdh|<Et> <dn>. <Mt> <yn> klo <hn> <at>
fi|yMMMMEEEEdhm|<Et> <dn>. <Mt> <yn> klo <hn>.<mN> <at>
fi|yMMMMEEEEdHm|<Et> <dn>. <Mt> <yn> klo <hn>.<mN>
fi|yMMMMEEEEdhmz|<Et> <dn>. <Mt> <yn> klo <hn>.<mN> <at> <zt>
fi|yMMMMEEEEdHms|<Et> <dn>. <Mt> <yn> klo <hn>.<mN>.<sN>
fi|yMMMMEEEEdHmzzzz|<Et> <dn>. <Mt> <yn> klo <hn>.<mN> <zt>
fi|EEEEdh|<Et> <dn>. klo <hn> <at>
fi|EEEEdhm|<Et> <dn>. klo <hn>.<mN> <at>
fi|EEEEdHm|<Et> <dn>. klo <hn>.<mN>
fi|EEEEdhmz|<Et> <dn>. klo <hn>.<mN> <at> <zt>
fi|EEEEdHms|<Et> <dn>. klo <hn>.<mN>.<sN>
fi|EEEEdHmzzzz|<Et> <dn>. klo <hn>.<mN> <zt>
fr-CA|d|<dn>
fr-CA|E|<Et>
fr-CA|Ed|<Et> <dn>
fr-CA|EEEE|<Et>
fr-CA|EEEEd|<Et> <dn>
fr-CA|M|<Mn>
fr-CA|Md|<MN>-<dN>
fr-CA|ME|<Mn> <Et>
fr-CA|MEd|<Et> <MN>-<dN>
fr-CA|MEEEE|<Mn> <Et>
fr-CA|MEEEEd|<Et> <MN>-<dN>
fr-CA|MM|<MN>
fr-CA|MMd|<MN>-<dN>
fr-CA|MME|<Et> (mois: <MN>)
fr-CA|MMEd|<Et> <MN>-<dN>
fr-CA|MMEEEE|<MN> <Et>
fr-CA|MMEEEEd|<Et> <MN>-<dN>
fr-CA|MMM|<Mt>
fr-CA|MMMd|<dn> <Mt>
fr-CA|MMME|<Mt> <Et>
fr-CA|MMMEd|<Et> <dn> <Mt>
fr-CA|MMMEEEE|<Mt> <Et>
fr-CA|MMMEEEEd|<Et> <dn> <Mt>
fr-CA|MMMM|<Mt>
fr-CA|MMMMd|<dn> <Mt>
fr-CA|MMMME|<Et> (mois: <Mt>)
fr-CA|MMMMEd|<Et> <dn> <Mt>
fr-CA|MMMMEEEE|<Mt> <Et>
fr-CA|MMMMEEEEd|<Et> <dn> <Mt>
fr-CA|y|<yn>
fr-CA|yd|<yn> (jour: <dn>)
fr-CA|yE|<yn> <Et>
fr-CA|yEd|<yn> <Et> <dn>
fr-CA|yEEEE|<yn> <Et>
fr-CA|yEEEEd|<yn> <Et> <dn>
fr-CA|yM|<yn>-<MN>
fr-CA|yMd|<yn>-<MN>-<dN>
fr-CA|yME|<yn>-<MN> <Et>
fr-CA|yMEd|<Et> <yn>-<MN>-<dN>
fr-CA|yMEEEE|<yn>-<MN> <Et>
fr-CA|yMEEEEd|<Et> <yn>-<MN>-<dN>
fr-CA|yMM|<yn>-<MN>
fr-CA|yMMd|<yn>-<MN>-<dn>
fr-CA|yMME|<yn>-<MN> <Et>
fr-CA|yMMEd|<Et> <yn>-<MN>-<dN>
fr-CA|yMMEEEE|<yn>-<MN> <Et>
fr-CA|yMMEEEEd|<Et> <yn>-<MN>-<dN>
fr-CA|yMMM|<Mt> <yn>
fr-CA|yMMMd|<dn> <Mt> <yn>
fr-CA|yMMME|<Mt> <yn> <Et>
fr-CA|yMMMEd|<Et> <dn> <Mt> <yn>
fr-CA|yMMMEEEE|<Mt> <yn> <Et>
fr-CA|yMMMEEEEd|<Et> <dn> <Mt> <yn>
fr-CA|yMMMM|<Mt> <yn>
fr-CA|yMMMMd|<dn> <Mt> <yn>
fr-CA|yMMMME|<Mt> <yn> <Et>
fr-CA|yMMMMEd|<Et> <dn> <Mt> <yn>
fr-CA|yMMMMEEEE|<Mt> <yn> <Et>
fr-CA|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
fr-CA|Gy|<yn> <Gt>
fr-CA|GyMMMd|<dn> <Mt> <yn> <Gt>
fr-CA|GyMMMMEEEEd|<Et> <dn> <Mt> <yn> <Gt>
fr-CA|GyMd|<yn>-<MN>-<dN> <Gt>
fr-CA|s|<sn>
fr-CA|sz|<sn> <zt>
fr-CA|szzzz|<sn> <zt>
fr-CA|m|<mn>
fr-CA|mz|<mn> <zt>
fr-CA|mzzzz|<mn> <zt>
fr-CA|ms|<mN> min <sN> s
fr-CA|msz|<mN> min <sN> s <zt>
fr-CA|mszzzz|<mN> min <sN> s <zt>
fr-CA|h|<hn> h <at>
fr-CA|hz|<hn> h <at> <zt>
fr-CA|hzzzz|<hn> h <at> <zt>
fr-CA|hs|<hn> h <at> (seconde: <sn>)
fr-CA|hsz|<hn> h <at> <zt> (seconde: <sn>)
fr-CA|hszzzz|<hn> h <at> <zt> (seconde: <sn>)
fr-CA|hm|<hn> h <mN> <at>
fr-CA|hmz|<hn> h <mN> <at> <zt>
fr-CA|hmzzzz|<hn> h <mN> <at> <zt>
fr-CA|hms|<hn> h <mN> min <sN> s <at>
fr-CA|hmsz|<hn> h <mN> min <sN> s <at> <zt>
fr-CA|hmszzzz|<hn> h <mN> min <sN> s <at> <zt>
fr-CA|H|<hN> h
fr-CA|Hz|<hn> h <zt>
fr-CA|Hzzzz|<hn> h <zt>
fr-CA|Hs|<hN> h (seconde: <sn>)
fr-CA|Hsz|<hn> h <zt> (seconde: <sn>)
fr-CA|Hszzzz|<hn> h <zt> (seconde: <sn>)
fr-CA|Hm|<hN> h <mN>
fr-CA|Hmz|<hN> h <mN> <zt>
fr-CA|Hmzzzz|<hN> h <mN> <zt>
fr-CA|Hms|<hN> h <mN> min <sN> s
fr-CA|Hmsz|<hn> h <mN> min <sN> s <zt>
fr-CA|Hmszzzz|<hn> h <mN> min <sN> s <zt>
fr-CA|yMMMdh|<dn> <Mt> <yn>, <hn> h <at>
fr-CA|yMMMdhm|<dn> <Mt> <yn>, <hn> h <mN> <at>
fr-CA|yMMMdHm|<dn> <Mt> <yn>, <hN> h <mN>
fr-CA|yMMMdhmz|<dn> <Mt> <yn>, <hn> h <mN> <at> <zt>
fr-CA|yMMMdHms|<dn> <Mt> <yn>, <hN> h <mN> min <sN> s
fr-CA|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN> h <mN> <zt>
fr-CA|MMMEdh|<Et> <dn> <Mt>, <hn> h <at>
fr-CA|MMMEdhm|<Et> <dn> <Mt>, <hn> h <mN> <at>
fr-CA|MMMEdHm|<Et> <dn> <Mt>, <hN> h <mN>
fr-CA|MMMEdhmz|<Et> <dn> <Mt>, <hn> h <mN> <at> <zt>
fr-CA|MMMEdHms|<Et> <dn> <Mt>, <hN> h <mN> min <sN> s
fr-CA|MMMEdHmzzzz|<Et> <dn> <Mt>, <hN> h <mN> <zt>
fr-CA|yMdh|<yn>-<MN>-<dN> <hn> h <at>
fr-CA|yMdhm|<yn>-<MN>-<dN> <hn> h <mN> <at>
fr-CA|yMdHm|<yn>-<MN>-<dN> <hN> h <mN>
fr-CA|yMdhmz|<yn>-<MN>-<dN> <hn> h <mN> <at> <zt>
fr-CA|yMdHms|<yn>-<MN>-<dN> <hN> h <mN> min <sN> s
fr-CA|yMdHmzzzz|<yn>-<MN>-<dN> <hN> h <mN> <zt>
fr-CA|Eh|<Et> <hn> h <at>
fr-CA|Ehm|<Et> <hn> h <mN> <at>
fr-CA|EHm|<Et> <hN> h <mN>
fr-CA|Ehmz|<Et> <hn> h <mN> <at> <zt>
fr-CA|EHms|<Et> <hN> h <mN> min <sN> s
fr-CA|EHmzzzz|<Et> <hN> h <mN> <zt>
fr-CA|dh|<dn> <hn> h <at>
fr-CA|dhm|<dn> <hn> h <mN> <at>
fr-CA|dHm|<dn> <hN> h <mN>
fr-CA|dhmz|<dn> <hn> h <mN> <at> <zt>
fr-CA|dHms|<dn> <hN> h <mN> min <sN> s
fr-CA|dHmzzzz|<dn> <hN> h <mN> <zt>
fr-CA|MMMMdh|<dn> <Mt> à <hn> h <at>
fr-CA|MMMMdhm|<dn> <Mt> à <hn> h <mN> <at>
fr-CA|MMMMdHm|<dn> <Mt> à <hN> h <mN>
fr-CA|MMMMdhmz|<dn> <Mt> à <hn> h <mN> <at> <zt>
fr-CA|MMMMdHms|<dn> <Mt> à <hN> h <mN> min <sN> s
fr-CA|MMMMdHmzzzz|<dn> <Mt> à <hN> h <mN> <zt>
fr-CA|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> à <hn> h <at>
fr-CA|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> à <hn> h <mN> <at>
fr-CA|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> à <hN> h <mN>
fr-CA|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> à <hn> h <mN> <at> <zt>
fr-CA|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> à <hN> h <mN> min <sN> s
fr-CA|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> à <hN> h <mN> <zt>
fr-CA|EEEEdh|<Et> <dn> <hn> h <at>
fr-CA|EEEEdhm|<Et> <dn> <hn> h <mN> <at>
fr-CA|EEEEdHm|<Et> <dn> <hN> h <mN>
fr-CA|EEEEdhmz|<Et> <dn> <hn> h <mN> <at> <zt>
fr-CA|EEEEdHms|<Et> <dn> <hN> h <mN> min <sN> s
fr-CA|EEEEdHmzzzz|<Et> <dn> <hN> h <mN> <zt>
fr|d|<dn>
fr|E|<Et>
fr|Ed|<Et> <dn>
fr|EEEE|<Et>
fr|EEEEd|<Et> <dn>
fr|M|<Mn>
fr|Md|<dN>/<MN>
fr|ME|<Mn> <Et>
fr|MEd|<Et> <dN>/<MN>
fr|MEEEE|<Mn> <Et>
fr|MEEEEd|<Et> <dN>/<MN>
fr|MM|<MN>
fr|MMd|<dN>/<MN>
fr|MME|<Et> (mois: <MN>)
fr|MMEd|<Et> <dN>/<MN>
fr|MMEEEE|<MN> <Et>
fr|MMEEEEd|<Et> <dN>/<MN>
fr|MMM|<Mt>
fr|MMMd|<dn> <Mt>
fr|MMME|<Mt> <Et>
fr|MMMEd|<Et> <dn> <Mt>
fr|MMMEEEE|<Mt> <Et>
fr|MMMEEEEd|<Et> <dn> <Mt>
fr|MMMM|<Mt>
fr|MMMMd|<dn> <Mt>
fr|MMMME|<Et> (mois: <Mt>)
fr|MMMMEd|<Et> <dn> <Mt>
fr|MMMMEEEE|<Mt> <Et>
fr|MMMMEEEEd|<Et> <dn> <Mt>
fr|y|<yn>
fr|yd|<yn> (jour: <dn>)
fr|yE|<yn> <Et>
fr|yEd|<yn> <Et> <dn>
fr|yEEEE|<yn> <Et>
fr|yEEEEd|<yn> <Et> <dn>
fr|yM|<MN>/<yn>
fr|yMd|<dN>/<MN>/<yn>
fr|yME|<MN>/<yn> <Et>
fr|yMEd|<Et> <dN>/<MN>/<yn>
fr|yMEEEE|<MN>/<yn> <Et>
fr|yMEEEEd|<Et> <dN>/<MN>/<yn>
fr|yMM|<MN>/<yn>
fr|yMMd|<dn>/<MN>/<yn>
fr|yMME|<MN>/<yn> <Et>
fr|yMMEd|<Et> <dN>/<MN>/<yn>
fr|yMMEEEE|<MN>/<yn> <Et>
fr|yMMEEEEd|<Et> <dN>/<MN>/<yn>
fr|yMMM|<Mt> <yn>
fr|yMMMd|<dn> <Mt> <yn>
fr|yMMME|<Mt> <yn> <Et>
fr|yMMMEd|<Et> <dn> <Mt> <yn>
fr|yMMMEEEE|<Mt> <yn> <Et>
fr|yMMMEEEEd|<Et> <dn> <Mt> <yn>
fr|yMMMM|<Mt> <yn>
fr|yMMMMd|<dn> <Mt> <yn>
fr|yMMMME|<Mt> <yn> <Et>
fr|yMMMMEd|<Et> <dn> <Mt> <yn>
fr|yMMMMEEEE|<Mt> <yn> <Et>
fr|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
fr|Gy|<yn> <Gt>
fr|GyMMMd|<dn> <Mt> <yn> <Gt>
fr|GyMMMMEEEEd|<Et> <dn> <Mt> <yn> <Gt>
fr|GyMd|<dN>/<MN>/<yn> <Gt>
fr|s|<sn>
fr|sz|<sn> <zt>
fr|szzzz|<sn> <zt>
fr|m|<mn>
fr|mz|<mn> <zt>
fr|mzzzz|<mn> <zt>
fr|ms|<mN>:<sN>
fr|msz|<mN>:<sN> <zt>
fr|mszzzz|<mN>:<sN> <zt>
fr|h|<hn> <at>
fr|hz|<hn> <at> <zt>
fr|hzzzz|<hn> <at> <zt>
fr|hs|<hn> <at> (seconde: <sn>)
fr|hsz|<hn> <at> <zt> (seconde: <sn>)
fr|hszzzz|<hn> <at> <zt> (seconde: <sn>)
fr|hm|<hn>:<mN> <at>
fr|hmz|<hn>:<mN> <at> <zt>
fr|hmzzzz|<hn>:<mN> <at> <zt>
fr|hms|<hn>:<mN>:<sN> <at>
fr|hmsz|<hn>:<mN>:<sN> <at> <zt>
fr|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
fr|H|<hN> h
fr|Hz|<hN> h <zt>
fr|Hzzzz|<hN> h <zt>
fr|Hs|<hN> h (seconde: <sn>)
fr|Hsz|<hN> h <zt> (seconde: <sn>)
fr|Hszzzz|<hN> h <zt> (seconde: <sn>)
fr|Hm|<hN>:<mN>
fr|Hmz|<hN>:<mN> <zt>
fr|Hmzzzz|<hN>:<mN> <zt>
fr|Hms|<hN>:<mN>:<sN>
fr|Hmsz|<hn>:<mN>:<sN> <zt>
fr|Hmszzzz|<hn>:<mN>:<sN> <zt>
fr|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
fr|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
fr|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
fr|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
fr|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
fr|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
fr|MMMEdh|<Et> <dn> <Mt>, <hn> <at>
fr|MMMEdhm|<Et> <dn> <Mt>, <hn>:<mN> <at>
fr|MMMEdHm|<Et> <dn> <Mt>, <hN>:<mN>
fr|MMMEdhmz|<Et> <dn> <Mt>, <hn>:<mN> <at> <zt>
fr|MMMEdHms|<Et> <dn> <Mt>, <hN>:<mN>:<sN>
fr|MMMEdHmzzzz|<Et> <dn> <Mt>, <hN>:<mN> <zt>
fr|yMdh|<dN>/<MN>/<yn> <hn> <at>
fr|yMdhm|<dN>/<MN>/<yn> <hn>:<mN> <at>
fr|yMdHm|<dN>/<MN>/<yn> <hN>:<mN>
fr|yMdhmz|<dN>/<MN>/<yn> <hn>:<mN> <at> <zt>
fr|yMdHms|<dN>/<MN>/<yn> <hN>:<mN>:<sN>
fr|yMdHmzzzz|<dN>/<MN>/<yn> <hN>:<mN> <zt>
fr|Eh|<Et> <hn> <at>
fr|Ehm|<Et> <hn>:<mN> <at>
fr|EHm|<Et> <hN>:<mN>
fr|Ehmz|<Et> <hn>:<mN> <at> <zt>
fr|EHms|<Et> <hN>:<mN>:<sN>
fr|EHmzzzz|<Et> <hN>:<mN> <zt>
fr|dh|<dn> <hn> <at>
fr|dhm|<dn> <hn>:<mN> <at>
fr|dHm|<dn> <hN>:<mN>
fr|dhmz|<dn> <hn>:<mN> <at> <zt>
fr|dHms|<dn> <hN>:<mN>:<sN>
fr|dHmzzzz|<dn> <hN>:<mN> <zt>
fr|MMMMdh|<dn> <Mt> à <hn> <at>
fr|MMMMdhm|<dn> <Mt> à <hn>:<mN> <at>
fr|MMMMdHm|<dn> <Mt> à <hN>:<mN>
fr|MMMMdhmz|<dn> <Mt> à <hn>:<mN> <at> <zt>
fr|MMMMdHms|<dn> <Mt> à <hN>:<mN>:<sN>
fr|MMMMdHmzzzz|<dn> <Mt> à <hN>:<mN> <zt>
fr|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> à <hn> <at>
fr|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> à <hn>:<mN> <at>
fr|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> à <hN>:<mN>
fr|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> à <hn>:<mN> <at> <zt>
fr|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> à <hN>:<mN>:<sN>
fr|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> à <hN>:<mN> <zt>
fr|EEEEdh|<Et> <dn> <hn> <at>
fr|EEEEdhm|<Et> <dn> <hn>:<mN> <at>
fr|EEEEdHm|<Et> <dn> <hN>:<mN>
fr|EEEEdhmz|<Et> <dn> <hn>:<mN> <at> <zt>
fr|EEEEdHms|<Et> <dn> <hN>:<mN>:<sN>
fr|EEEEdHmzzzz|<Et> <dn> <hN>:<mN> <zt>
gl|d|<dn>
gl|E|<Et>
gl|Ed|<Et> <dn>
gl|EEEE|<Et>
gl|EEEEd|<Et> <dn>
gl|M|<Mn>
gl|Md|<dn>/<Mn>
gl|ME|<Mn> <Et>
gl|MEd|<Et>, <dn>/<Mn>
gl|MEEEE|<Mn> <Et>
gl|MEEEEd|<Et>, <dn>/<Mn>
gl|MM|<MN>
gl|MMd|<dn>/<MN>
gl|MME|<Et> (mes: <MN>)
gl|MMEd|<Et>, <dn>/<MN>
gl|MMEEEE|<MN> <Et>
gl|MMEEEEd|<Et>, <dn>/<MN>
gl|MMM|<Mt>
gl|MMMd|<dn> de <Mt>
gl|MMME|<Mt> <Et>
gl|MMMEd|<Et>, <dn> de <Mt>
gl|MMMEEEE|<Mt> <Et>
gl|MMMEEEEd|<Et>, <dn> de <Mt>
gl|MMMM|<Mt>
gl|MMMMd|<dn> de <Mt>
gl|MMMME|<Et> (mes: <Mt>)
gl|MMMMEd|<Et>, <dn> de <Mt>
gl|MMMMEEEE|<Mt> <Et>
gl|MMMMEEEEd|<Et>, <dn> de <Mt>
gl|y|<yn>
gl|yd|<yn> (día: <dn>)
gl|yE|<yn> <Et>
gl|yEd|<yn> <Et> <dn>
gl|yEEEE|<yn> <Et>
gl|yEEEEd|<yn> <Et> <dn>
gl|yM|<Mn>/<yn>
gl|yMd|<dn>/<Mn>/<yn>
gl|yME|<Mn>/<yn> <Et>
gl|yMEd|<Et>, <dn>/<Mn>/<yn>
gl|yMEEEE|<Mn>/<yn> <Et>
gl|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
gl|yMM|<MN>/<yn>
gl|yMMd|<dn>/<MN>/<yn>
gl|yMME|<MN>/<yn> <Et>
gl|yMMEd|<Et>, <dn>/<MN>/<yn>
gl|yMMEEEE|<MN>/<yn> <Et>
gl|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
gl|yMMM|<Mt> de <yn>
gl|yMMMd|<dn> de <Mt> de <yn>
gl|yMMME|<Mt> de <yn> <Et>
gl|yMMMEd|<Et>, <dn> de <Mt> de <yn>
gl|yMMMEEEE|<Mt> de <yn> <Et>
gl|yMMMEEEEd|<Et>, <dn> de <Mt> de <yn>
gl|yMMMM|<Mt> de <yn>
gl|yMMMMd|<dn> de <Mt> de <yn>
gl|yMMMME|<Mt> de <yn> <Et>
gl|yMMMMEd|<Et>, <dn> de <Mt> de <yn>
gl|yMMMMEEEE|<Mt> de <yn> <Et>
gl|yMMMMEEEEd|<Et>, <dn> de <Mt> de <yn>
gl|Gy|<yn> <Gt>
gl|GyMMMd|<dn> de <Mt> de <yn> <Gt>
gl|GyMMMMEEEEd|<Et>, <dn> de <Mt> de <yn> <Gt>
gl|GyMd|<dn>/<Mn>/<yn> <Gt>
gl|s|<sn>
gl|sz|<sn> <zt>
gl|szzzz|<sn> <zt>
gl|m|<mn>
gl|mz|<mn> <zt>
gl|mzzzz|<mn> <zt>
gl|ms|<mN>:<sN>
gl|msz|<mN>:<sN> <zt>
gl|mszzzz|<mN>:<sN> <zt>
gl|h|<hn> <at>
gl|hz|<hn> <at> <zt>
gl|hzzzz|<hn> <at> <zt>
gl|hs|<hn> <at> (segundo: <sn>)
gl|hsz|<hn> <at> <zt> (segundo: <sn>)
gl|hszzzz|<hn> <at> <zt> (segundo: <sn>)
gl|hm|<hn>:<mN> <at>
gl|hmz|<hn>:<mN> <at> <zt>
gl|hmzzzz|<hn>:<mN> <at> <zt>
gl|hms|<hn>:<mN>:<sN> <at>
gl|hmsz|<hn>:<mN>:<sN> <at> <zt>
gl|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
gl|H|<hN>
gl|Hz|<hN> h <zt>
gl|Hzzzz|<hN> h <zt>
gl|Hs|<hN> (segundo: <sn>)
gl|Hsz|<hN> h <zt> (segundo: <sn>)
gl|Hszzzz|<hN> h <zt> (segundo: <sn>)
gl|Hm|<hN>:<mN>
gl|Hmz|<hN>:<mN> <zt>
gl|Hmzzzz|<hN>:<mN> <zt>
gl|Hms|<hN>:<mN>:<sN>
gl|Hmsz|<hn>:<mN>:<sN> <zt>
gl|Hmszzzz|<hn>:<mN>:<sN> <zt>
gl|yMMMdh|<dn> de <Mt> de <yn>, <hn> <at>
gl|yMMMdhm|<dn> de <Mt> de <yn>, <hn>:<mN> <at>
gl|yMMMdHm|<dn> de <Mt> de <yn>, <hN>:<mN>
gl|yMMMdhmz|<dn> de <Mt> de <yn>, <hn>:<mN> <at> <zt>
gl|yMMMdHms|<dn> de <Mt> de <yn>, <hN>:<mN>:<sN>
gl|yMMMdHmzzzz|<dn> de <Mt> de <yn>, <hN>:<mN> <zt>
gl|MMMEdh|<Et>, <dn> de <Mt>, <hn> <at>
gl|MMMEdhm|<Et>, <dn> de <Mt>, <hn>:<mN> <at>
gl|MMMEdHm|<Et>, <dn> de <Mt>, <hN>:<mN>
gl|MMMEdhmz|<Et>, <dn> de <Mt>, <hn>:<mN> <at> <zt>
gl|MMMEdHms|<Et>, <dn> de <Mt>, <hN>:<mN>:<sN>
gl|MMMEdHmzzzz|<Et>, <dn> de <Mt>, <hN>:<mN> <zt>
gl|yMdh|<dn>/<Mn>/<yn>, <hn> <at>
gl|yMdhm|<dn>/<Mn>/<yn>, <hn>:<mN> <at>
gl|yMdHm|<dn>/<Mn>/<yn>, <hN>:<mN>
gl|yMdhmz|<dn>/<Mn>/<yn>, <hn>:<mN> <at> <zt>
gl|yMdHms|<dn>/<Mn>/<yn>, <hN>:<mN>:<sN>
gl|yMdHmzzzz|<dn>/<Mn>/<yn>, <hN>:<mN> <zt>
gl|Eh|<Et>, <hn> <at>
gl|Ehm|<Et>, <hn>:<mN> <at>
gl|EHm|<Et>, <hN>:<mN>
gl|Ehmz|<Et>, <hn>:<mN> <at> <zt>
gl|EHms|<Et>, <hN>:<mN>:<sN>
gl|EHmzzzz|<Et>, <hN>:<mN> <zt>
gl|dh|<dn>, <hn> <at>
gl|dhm|<dn>, <hn>:<mN> <at>
gl|dHm|<dn>, <hN>:<mN>
gl|dhmz|<dn>, <hn>:<mN> <at> <zt>
gl|dHms|<dn>, <hN>:<mN>:<sN>
gl|dHmzzzz|<dn>, <hN>:<mN> <zt>
gl|MMMMdh|<dn> de <Mt>, <hn> <at>
gl|MMMMdhm|<dn> de <Mt>, <hn>:<mN> <at>
gl|MMMMdHm|<dn> de <Mt>, <hN>:<mN>
gl|MMMMdhmz|<dn> de <Mt>, <hn>:<mN> <at> <zt>
gl|MMMMdHms|<dn> de <Mt>, <hN>:<mN>:<sN>
gl|MMMMdHmzzzz|<dn> de <Mt>, <hN>:<mN> <zt>
gl|yMMMMEEEEdh|<Et>, <dn> de <Mt> de <yn>, <hn> <at>
gl|yMMMMEEEEdhm|<Et>, <dn> de <Mt> de <yn>, <hn>:<mN> <at>
gl|yMMMMEEEEdHm|<Et>, <dn> de <Mt> de <yn>, <hN>:<mN>
gl|yMMMMEEEEdhmz|<Et>, <dn> de <Mt> de <yn>, <hn>:<mN> <at> <zt>
gl|yMMMMEEEEdHms|<Et>, <dn> de <Mt> de <yn>, <hN>:<mN>:<sN>
gl|yMMMMEEEEdHmzzzz|<Et>, <dn> de <Mt> de <yn>, <hN>:<mN> <zt>
gl|EEEEdh|<Et> <dn>, <hn> <at>
gl|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
gl|EEEEdHm|<Et> <dn>, <hN>:<mN>
gl|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
gl|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
gl|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
he|d|<dn>
he|E|<Et>
he|Ed|<Et> ה-<dn>
he|EEEE|<Et>
he|EEEEd|<Et> ה-<dn>
he|M|<Mn>
he|Md|<dn>.<Mn>
he|ME|<Mn> <Et>
he|MEd|<Et>, <dn>.<Mn>
he|MEEEE|<Mn> <Et>
he|MEEEEd|<Et>, <dn>.<Mn>
he|MM|<MN>
he|MMd|<dn>.<MN>
he|MME|<Et> (חודש: <MN>)
he|MMEd|<Et>, <dn>.<MN>
he|MMEEEE|<MN> <Et>
he|MMEEEEd|<Et>, <dn>.<MN>
he|MMM|<Mt>
he|MMMd|<dn> ב<Mt>
he|MMME|<Mt> <Et>
he|MMMEd|<Et>, <dn> ב<Mt>
he|MMMEEEE|<Mt> <Et>
he|MMMEEEEd|<Et>, <dn> ב<Mt>
he|MMMM|<Mt>
he|MMMMd|<dn> ב<Mt>
he|MMMME|<Et> (חודש: <Mt>)
he|MMMMEd|<Et>, <dn> ב<Mt>
he|MMMMEEEE|<Mt> <Et>
he|MMMMEEEEd|<Et>, <dn> ב<Mt>
he|y|<yn>
he|yd|<yn> (יום: <dn>)
he|yE|<yn> <Et>
he|yEd|<yn> <Et> ה-<dn>
he|yEEEE|<yn> <Et>
he|yEEEEd|<yn> <Et> ה-<dn>
he|yM|<Mn>.<yn>
he|yMd|<dn>.<Mn>.<yn>
he|yME|<Mn>.<yn> <Et>
he|yMEd|<Et>, <dn>.<Mn>.<yn>
he|yMEEEE|<Mn>.<yn> <Et>
he|yMEEEEd|<Et>, <dn>.<Mn>.<yn>
he|yMM|<Mn>.<yn>
he|yMMd|<dn>.<MN>.<yn>
he|yMME|<Mn>.<yn> <Et>
he|yMMEd|<Et>, <dn>.<MN>.<yn>
he|yMMEEEE|<Mn>.<yn> <Et>
he|yMMEEEEd|<Et>, <dn>.<MN>.<yn>
he|yMMM|<Mt> <yn>
he|yMMMd|<dn> ב<Mt> <yn>
he|yMMME|<Mt> <yn> <Et>
he|yMMMEd|<Et>, <dn> ב<Mt> <yn>
he|yMMMEEEE|<Mt> <yn> <Et>
he|yMMMEEEEd|<Et>, <dn> ב<Mt> <yn>
he|yMMMM|<Mt> <yn>
he|yMMMMd|<dn> ב<Mt> <yn>
he|yMMMME|<Mt> <yn> <Et>
he|yMMMMEd|<Et>, <dn> ב<Mt> <yn>
he|yMMMMEEEE|<Mt> <yn> <Et>
he|yMMMMEEEEd|<Et>, <dn> ב<Mt> <yn>
he|Gy|<yn> <Gt>
he|GyMMMd|<dn> ב<Mt> <yn> <Gt>
he|GyMMMMEEEEd|<Et>, <dn> ב<Mt> <yn> <Gt>
he|GyMd|<dn>/<Mn>/<yn> <Gt>
he|s|<sn>
he|sz|<sn> <zt>
he|szzzz|<sn> <zt>
he|m|<mn>
he|mz|<mn> <zt>
he|mzzzz|<mn> <zt>
he|ms|<mN>:<sN>
he|msz|<mN>:<sN> <zt>
he|mszzzz|<mN>:<sN> <zt>
he|h|<hn> <at>
he|hz|<hn> <at> <zt>
he|hzzzz|<hn> <at> <zt>
he|hs|<hn> <at> (שנייה: <sn>)
he|hsz|<hn> <at> <zt> (שנייה: <sn>)
he|hszzzz|<hn> <at> <zt> (שנייה: <sn>)
he|hm|<hn>:<mN> <at>
he|hmz|<hn>:<mN> <at> <zt>
he|hmzzzz|<hn>:<mN> <at> <zt>
he|hms|<hn>:<mN>:<sN> <at>
he|hmsz|<hn>:<mN>:<sN> <at> <zt>
he|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
he|H|<hn>
he|Hz|<hN>h <zt>
he|Hzzzz|<hN>h <zt>
he|Hs|<hn> (שנייה: <sn>)
he|Hsz|<hN>h <zt> (שנייה: <sn>)
he|Hszzzz|<hN>h <zt> (שנייה: <sn>)
he|Hm|<hn>:<mN>
he|Hmz|<hN>:<mN> <zt>
he|Hmzzzz|<hN>:<mN> <zt>
he|Hms|<hn>:<mN>:<sN>
he|Hmsz|<hn>:<mN>:<sN> <zt>
he|Hmszzzz|<hn>:<mN>:<sN> <zt>
he|yMMMdh|<dn> ב<Mt> <yn>, <hn> <at>
he|yMMMdhm|<dn> ב<Mt> <yn>, <hn>:<mN> <at>
he|yMMMdHm|<dn> ב<Mt> <yn>, <hn>:<mN>
he|yMMMdhmz|<dn> ב<Mt> <yn>, <hn>:<mN> <at> <zt>
he|yMMMdHms|<dn> ב<Mt> <yn>, <hn>:<mN>:<sN>
he|yMMMdHmzzzz|<dn> ב<Mt> <yn>, <hN>:<mN> <zt>
he|MMMEdh|<Et>, <dn> ב<Mt>, <hn> <at>
he|MMMEdhm|<Et>, <dn> ב<Mt>, <hn>:<mN> <at>
he|MMMEdHm|<Et>, <dn> ב<Mt>, <hn>:<mN>
he|MMMEdhmz|<Et>, <dn> ב<Mt>, <hn>:<mN> <at> <zt>
he|MMMEdHms|<Et>, <dn> ב<Mt>, <hn>:<mN>:<sN>
he|MMMEdHmzzzz|<Et>, <dn> ב<Mt>, <hN>:<mN> <zt>
he|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
he|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
he|yMdHm|<dn>.<Mn>.<yn>, <hn>:<mN>
he|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at> <zt>
he|yMdHms|<dn>.<Mn>.<yn>, <hn>:<mN>:<sN>
he|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>:<mN> <zt>
he|Eh|<Et> <hn> <at>
he|Ehm|<Et> <hn>:<mN> <at>
he|EHm|<Et> <hn>:<mN>
he|Ehmz|<Et>, <hn>:<mN> <at> <zt>
he|EHms|<Et> <hn>:<mN>:<sN>
he|EHmzzzz|<Et>, <hN>:<mN> <zt>
he|dh|<dn>, <hn> <at>
he|dhm|<dn>, <hn>:<mN> <at>
he|dHm|<dn>, <hn>:<mN>
he|dhmz|<dn>, <hn>:<mN> <at> <zt>
he|dHms|<dn>, <hn>:<mN>:<sN>
he|dHmzzzz|<dn>, <hN>:<mN> <zt>
he|MMMMdh|<dn> ב<Mt> בשעה <hn> <at>
he|MMMMdhm|<dn> ב<Mt> בשעה <hn>:<mN> <at>
he|MMMMdHm|<dn> ב<Mt> בשעה <hn>:<mN>
he|MMMMdhmz|<dn> ב<Mt> בשעה <hn>:<mN> <at> <zt>
he|MMMMdHms|<dn> ב<Mt> בשעה <hn>:<mN>:<sN>
he|MMMMdHmzzzz|<dn> ב<Mt> בשעה <hN>:<mN> <zt>
he|yMMMMEEEEdh|<Et>, <dn> ב<Mt> <yn> בשעה <hn> <at>
he|yMMMMEEEEdhm|<Et>, <dn> ב<Mt> <yn> בשעה <hn>:<mN> <at>
he|yMMMMEEEEdHm|<Et>, <dn> ב<Mt> <yn> בשעה <hn>:<mN>
he|yMMMMEEEEdhmz|<Et>, <dn> ב<Mt> <yn> בשעה <hn>:<mN> <at> <zt>
he|yMMMMEEEEdHms|<Et>, <dn> ב<Mt> <yn> בשעה <hn>:<mN>:<sN>
he|yMMMMEEEEdHmzzzz|<Et>, <dn> ב<Mt> <yn> בשעה <hN>:<mN> <zt>
he|EEEEdh|<Et> ה-<dn>, <hn> <at>
he|EEEEdhm|<Et> ה-<dn>, <hn>:<mN> <at>
he|EEEEdHm|<Et> ה-<dn>, <hn>:<mN>
he|EEEEdhmz|<Et> ה-<dn>, <hn>:<mN> <at> <zt>
he|EEEEdHms|<Et> ה-<dn>, <hn>:<mN>:<sN>
he|EEEEdHmzzzz|<Et> ה-<dn>, <hN>:<mN> <zt>
hi|d|<dn>
hi|E|<Et>
hi|Ed|<Et> <dn>
hi|EEEE|<Et>
hi|EEEEd|<Et> <dn>
hi|M|<Mn>
hi|Md|<dn>/<Mn>
hi|ME|<Mn> <Et>
hi|MEd|<Et>, <dn>/<Mn>
hi|MEEEE|<Mn> <Et>
hi|MEEEEd|<Et>, <dn>/<Mn>
hi|MM|<MN>
hi|MMd|<dn>/<MN>
hi|MME|<Et> (माह: <MN>)
hi|MMEd|<Et>, <dn>/<MN>
hi|MMEEEE|<MN> <Et>
hi|MMEEEEd|<Et>, <dn>/<MN>
hi|MMM|<Mt>
hi|MMMd|<dn> <Mt>
hi|MMME|<Mt> <Et>
hi|MMMEd|<Et>, <dn> <Mt>
hi|MMMEEEE|<Mt> <Et>
hi|MMMEEEEd|<Et>, <dn> <Mt>
hi|MMMM|<Mt>
hi|MMMMd|<dn> <Mt>
hi|MMMME|<Et> (माह: <Mt>)
hi|MMMMEd|<Et>, <dn> <Mt>
hi|MMMMEEEE|<Mt> <Et>
hi|MMMMEEEEd|<Et>, <dn> <Mt>
hi|y|<yn>
hi|yd|<yn> (दिन: <dn>)
hi|yE|<yn> <Et>
hi|yEd|<yn> <Et> <dn>
hi|yEEEE|<yn> <Et>
hi|yEEEEd|<yn> <Et> <dn>
hi|yM|<Mn>/<yn>
hi|yMd|<dn>/<Mn>/<yn>
hi|yME|<Mn>/<yn> <Et>
hi|yMEd|<Et>, <dn>/<Mn>/<yn>
hi|yMEEEE|<Mn>/<yn> <Et>
hi|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
hi|yMM|<MN>/<yn>
hi|yMMd|<dn>/<MN>/<yn>
hi|yMME|<MN>/<yn> <Et>
hi|yMMEd|<Et>, <dn>/<MN>/<yn>
hi|yMMEEEE|<MN>/<yn> <Et>
hi|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
hi|yMMM|<Mt> <yn>
hi|yMMMd|<dn> <Mt> <yn>
hi|yMMME|<Mt> <yn> <Et>
hi|yMMMEd|<Et>, <dn> <Mt> <yn>
hi|yMMMEEEE|<Mt> <yn> <Et>
hi|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
hi|yMMMM|<Mt> <yn>
hi|yMMMMd|<dn> <Mt> <yn>
hi|yMMMME|<Mt> <yn> <Et>
hi|yMMMMEd|<Et>, <dn> <Mt> <yn>
hi|yMMMMEEEE|<Mt> <yn> <Et>
hi|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
hi|Gy|<yn> <Gt>
hi|GyMMMd|<dn> <Mt> <yn> <Gt>
hi|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
hi|GyMd|<Gt> <dn>/<Mn>/<yn>
hi|s|<sn>
hi|sz|<sn> <zt>
hi|szzzz|<sn> <zt>
hi|m|<mn>
hi|mz|<mn> <zt>
hi|mzzzz|<mn> <zt>
hi|ms|<mN>:<sN>
hi|msz|<mN>:<sN> <zt>
hi|mszzzz|<mN>:<sN> <zt>
hi|h|<hn> <at>
hi|hz|<hn> <at> <zt>
hi|hzzzz|<hn> <at> <zt>
hi|hs|<hn> <at> (सेकंड: <sn>)
hi|hsz|<hn> <at> <zt> (सेकंड: <sn>)
hi|hszzzz|<hn> <at> <zt> (सेकंड: <sn>)
hi|hm|<hn>:<mN> <at>
hi|hmz|<hn>:<mN> <at> <zt>
hi|hmzzzz|<hn>:<mN> <at> <zt>
hi|hms|<hn>:<mN>:<sN> <at>
hi|hmsz|<hn>:<mN>:<sN> <at> <zt>
hi|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
hi|H|<hN>
hi|Hz|<hN>h <zt>
hi|Hzzzz|<hN>h <zt>
hi|Hs|<hN> (सेकंड: <sn>)
hi|Hsz|<hN>h <zt> (सेकंड: <sn>)
hi|Hszzzz|<hN>h <zt> (सेकंड: <sn>)
hi|Hm|<hN>:<mN>
hi|Hmz|<hN>:<mN> <zt>
hi|Hmzzzz|<hN>:<mN> <zt>
hi|Hms|<hN>:<mN>:<sN>
hi|Hmsz|<hN>:<mN>:<sN> <zt>
hi|Hmszzzz|<hN>:<mN>:<sN> <zt>
hi|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
hi|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
hi|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
hi|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
hi|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
hi|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
hi|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
hi|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
hi|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
hi|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
hi|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
hi|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
hi|yMdh|<dn>/<Mn>/<yn>, <hn> <at>
hi|yMdhm|<dn>/<Mn>/<yn>, <hn>:<mN> <at>
hi|yMdHm|<dn>/<Mn>/<yn>, <hN>:<mN>
hi|yMdhmz|<dn>/<Mn>/<yn>, <hn>:<mN> <at> <zt>
hi|yMdHms|<dn>/<Mn>/<yn>, <hN>:<mN>:<sN>
hi|yMdHmzzzz|<dn>/<Mn>/<yn>, <hN>:<mN> <zt>
hi|Eh|<Et> <hn> <at>
hi|Ehm|<Et> <hn>:<mN> <at>
hi|EHm|<Et> <hN>:<mN>
hi|Ehmz|<Et>, <hn>:<mN> <at> <zt>
hi|EHms|<Et> <hN>:<mN>:<sN>
hi|EHmzzzz|<Et>, <hN>:<mN> <zt>
hi|dh|<dn>, <hn> <at>
hi|dhm|<dn>, <hn>:<mN> <at>
hi|dHm|<dn>, <hN>:<mN>
hi|dhmz|<dn>, <hn>:<mN> <at> <zt>
hi|dHms|<dn>, <hN>:<mN>:<sN>
hi|dHmzzzz|<dn>, <hN>:<mN> <zt>
hi|MMMMdh|<dn> <Mt> को <hn> <at> बजे
hi|MMMMdhm|<dn> <Mt> को <hn>:<mN> <at> बजे
hi|MMMMdHm|<dn> <Mt> को <hN>:<mN> बजे
hi|MMMMdhmz|<dn> <Mt> को <hn>:<mN> <at> <zt> बजे
hi|MMMMdHms|<dn> <Mt> को <hN>:<mN>:<sN> बजे
hi|MMMMdHmzzzz|<dn> <Mt> को <hN>:<mN> <zt> बजे
hi|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> को <hn> <at> बजे
hi|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> को <hn>:<mN> <at> बजे
hi|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> को <hN>:<mN> बजे
hi|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> को <hn>:<mN> <at> <zt> बजे
hi|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> को <hN>:<mN>:<sN> बजे
hi|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> को <hN>:<mN> <zt> बजे
hi|EEEEdh|<Et> <dn>, <hn> <at>
hi|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
hi|EEEEdHm|<Et> <dn>, <hN>:<mN>
hi|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
hi|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
hi|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
hr|d|<dn>.
hr|E|<Et>
hr|Ed|<Et>, <dn>.
hr|EEEE|<Et>
hr|EEEEd|<Et>, <dn>.
hr|M|<Mn>.
hr|Md|<dN>. <MN>.
hr|ME|<Mn>. <Et>
hr|MEd|<Et>, <dN>. <MN>.
hr|MEEEE|<Mn>. <Et>
hr|MEEEEd|<Et>, <dN>. <MN>.
hr|MM|<MN>.
hr|MMd|<dn>. <MN>.
hr|MME|<Et> (mjesec: <MN>.)
hr|MMEd|<Et>, <dN>. <MN>.
hr|MMEEEE|<MN>. <Et>
hr|MMEEEEd|<Et>, <dN>. <MN>.
hr|MMM|<Mt>
hr|MMMd|<dn>. <Mt>
hr|MMME|<Mt> <Et>
hr|MMMEd|<Et>, <dn>. <Mt>
hr|MMMEEEE|<Mt> <Et>
hr|MMMEEEEd|<Et>, <dn>. <Mt>
hr|MMMM|<Mt>
hr|MMMMd|<dn>. <Mt>
hr|MMMME|<Et> (mjesec: <Mt>)
hr|MMMMEd|<Et>, <dn>. <Mt>
hr|MMMMEEEE|<Mt> <Et>
hr|MMMMEEEEd|<Et>, <dn>. <Mt>
hr|y|<yn>.
hr|yd|<yn>. (dan: <dn>.)
hr|yE|<yn>. <Et>
hr|yEd|<yn>. <Et>, <dn>.
hr|yEEEE|<yn>. <Et>
hr|yEEEEd|<yn>. <Et>, <dn>.
hr|yM|<MN>. <yn>.
hr|yMd|<dN>. <MN>. <yn>.
hr|yME|<MN>. <yn>. <Et>
hr|yMEd|<Et>, <dN>. <MN>. <yn>.
hr|yMEEEE|<MN>. <yn>. <Et>
hr|yMEEEEd|<Et>, <dN>. <MN>. <yn>.
hr|yMM|<MN>. <yn>.
hr|yMMd|<dn>. <MN>. <yn>.
hr|yMME|<MN>. <yn>. <Et>
hr|yMMEd|<Et>, <dN>. <MN>. <yn>.
hr|yMMEEEE|<MN>. <yn>. <Et>
hr|yMMEEEEd|<Et>, <dN>. <MN>. <yn>.
hr|yMMM|<Mt> <yn>.
hr|yMMMd|<dn>. <Mt> <yn>.
hr|yMMME|<Mt> <yn>. <Et>
hr|yMMMEd|<Et>, <dn>. <Mt> <yn>.
hr|yMMMEEEE|<Mt> <yn>. <Et>
hr|yMMMEEEEd|<Et>, <dn>. <Mt> <yn>.
hr|yMMMM|<Mt> <yn>.
hr|yMMMMd|<dn>. <Mt> <yn>.
hr|yMMMME|<Mt> <yn>. <Et>
hr|yMMMMEd|<Et>, <dn>. <Mt> <yn>.
hr|yMMMMEEEE|<Mt> <yn>. <Et>
hr|yMMMMEEEEd|<Et>, <dn>. <Mt> <yn>.
hr|Gy|<yn>. <Gt>
hr|GyMMMd|<dn>. <Mt> <yn>. <Gt>
hr|GyMMMMEEEEd|<Et>, <dn>. <Mt> <yn>. <Gt>
hr|GyMd|<dn>. <Mn>. <yn>. <Gt>
hr|s|<sn>
hr|sz|<sn> (<zt>)
hr|szzzz|<sn> (<zt>)
hr|m|<mn>
hr|mz|<mn> (<zt>)
hr|mzzzz|<mn> (<zt>)
hr|ms|<mN>:<sN>
hr|msz|<mN>:<sN> (<zt>)
hr|mszzzz|<mN>:<sN> (<zt>)
hr|h|<hn> <at>
hr|hz|<hn> <at> <zt>
hr|hzzzz|<hn> <at> <zt>
hr|hs|<hn> <at> (sekunda: <sn>)
hr|hsz|<hn> <at> <zt> (sekunda: <sn>)
hr|hszzzz|<hn> <at> <zt> (sekunda: <sn>)
hr|hm|<hN>:<mN> <at>
hr|hmz|<hn>:<mN> <at> <zt>
hr|hmzzzz|<hn>:<mN> <at> <zt>
hr|hms|<hN>:<mN>:<sN> <at>
hr|hmsz|<hn>:<mN>:<sN> <at> <zt>
hr|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
hr|H|<hN>
hr|Hz|<hN> h <zt>
hr|Hzzzz|<hN> h <zt>
hr|Hs|<hN> (sekunda: <sn>)
hr|Hsz|<hN> h <zt> (sekunda: <sn>)
hr|Hszzzz|<hN> h <zt> (sekunda: <sn>)
hr|Hm|<hN>:<mN>
hr|Hmz|<hN>:<mN> <zt>
hr|Hmzzzz|<hN>:<mN> <zt>
hr|Hms|<hN>:<mN>:<sN>
hr|Hmsz|<hn>:<mN>:<sN> <zt>
hr|Hmszzzz|<hn>:<mN>:<sN> (<zt>)
hr|yMMMdh|<dn>. <Mt> <yn>. <hn> <at>
hr|yMMMdhm|<dn>. <Mt> <yn>. <hN>:<mN> <at>
hr|yMMMdHm|<dn>. <Mt> <yn>. <hN>:<mN>
hr|yMMMdhmz|<dn>. <Mt> <yn>. <hn>:<mN> <at> <zt>
hr|yMMMdHms|<dn>. <Mt> <yn>. <hN>:<mN>:<sN>
hr|yMMMdHmzzzz|<dn>. <Mt> <yn>. <hN>:<mN> <zt>
hr|MMMEdh|<Et>, <dn>. <Mt> <hn> <at>
hr|MMMEdhm|<Et>, <dn>. <Mt> <hN>:<mN> <at>
hr|MMMEdHm|<Et>, <dn>. <Mt> <hN>:<mN>
hr|MMMEdhmz|<Et>, <dn>. <Mt> <hn>:<mN> <at> <zt>
hr|MMMEdHms|<Et>, <dn>. <Mt> <hN>:<mN>:<sN>
hr|MMMEdHmzzzz|<Et>, <dn>. <Mt> <hN>:<mN> <zt>
hr|yMdh|<dN>. <MN>. <yn>. <hn> <at>
hr|yMdhm|<dN>. <MN>. <yn>. <hN>:<mN> <at>
hr|yMdHm|<dN>. <MN>. <yn>. <hN>:<mN>
hr|yMdhmz|<dN>. <MN>. <yn>. <hn>:<mN> <at> <zt>
hr|yMdHms|<dN>. <MN>. <yn>. <hN>:<mN>:<sN>
hr|yMdHmzzzz|<dN>. <MN>. <yn>. <hN>:<mN> <zt>
hr|Eh|<Et> <hn> <at>
hr|Ehm|<Et> <hn>:<mN> <at>
hr|EHm|<Et> <hN>:<mN>
hr|Ehmz|<Et> <hn>:<mN> <at> <zt>
hr|EHms|<Et> <hN>:<mN>:<sN>
hr|EHmzzzz|<Et> <hN>:<mN> <zt>
hr|dh|<dn>. <hn> <at>
hr|dhm|<dn>. <hN>:<mN> <at>
hr|dHm|<dn>. <hN>:<mN>
hr|dhmz|<dn>. <hn>:<mN> <at> <zt>
hr|dHms|<dn>. <hN>:<mN>:<sN>
hr|dHmzzzz|<dn>. <hN>:<mN> <zt>
hr|MMMMdh|<dn>. <Mt> u <hn> <at>
hr|MMMMdhm|<dn>. <Mt> u <hN>:<mN> <at>
hr|MMMMdHm|<dn>. <Mt> u <hN>:<mN>
hr|MMMMdhmz|<dn>. <Mt> u <hn>:<mN> <at> <zt>
hr|MMMMdHms|<dn>. <Mt> u <hN>:<mN>:<sN>
hr|MMMMdHmzzzz|<dn>. <Mt> u <hN>:<mN> <zt>
hr|yMMMMEEEEdh|<Et>, <dn>. <Mt> <yn>. u <hn> <at>
hr|yMMMMEEEEdhm|<Et>, <dn>. <Mt> <yn>. u <hN>:<mN> <at>
hr|yMMMMEEEEdHm|<Et>, <dn>. <Mt> <yn>. u <hN>:<mN>
hr|yMMMMEEEEdhmz|<Et>, <dn>. <Mt> <yn>. u <hn>:<mN> <at> <zt>
hr|yMMMMEEEEdHms|<Et>, <dn>. <Mt> <yn>. u <hN>:<mN>:<sN>
hr|yMMMMEEEEdHmzzzz|<Et>, <dn>. <Mt> <yn>. u <hN>:<mN> <zt>
hr|EEEEdh|<Et>, <dn>. <hn> <at>
hr|EEEEdhm|<Et>, <dn>. <hN>:<mN> <at>
hr|EEEEdHm|<Et>, <dn>. <hN>:<mN>
hr|EEEEdhmz|<Et>, <dn>. <hn>:<mN> <at> <zt>
hr|EEEEdHms|<Et>, <dn>. <hN>:<mN>:<sN>
hr|EEEEdHmzzzz|<Et>, <dn>. <hN>:<mN> <zt>
hu|d|<dn>
hu|E|<Et>
hu|Ed|<dn>., <Et>
hu|EEEE|<Et>
hu|EEEEd|<dn>., <Et>
hu|M|<Mn>
hu|Md|<Mn>. <dn>.
hu|ME|<Mn> <Et>
hu|MEd|<Mn>. <dn>., <Et>
hu|MEEEE|<Mn> <Et>
hu|MEEEEd|<Mn>. <dn>., <Et>
hu|MM|<MN>
hu|MMd|<MN>. <dn>.
hu|MME|<Et> (hónap: <MN>)
hu|MMEd|<MN>. <dn>., <Et>
hu|MMEEEE|<MN> <Et>
hu|MMEEEEd|<MN>. <dn>., <Et>
hu|MMM|<Mt>
hu|MMMd|<Mt> <dn>.
hu|MMME|<Mt> <Et>
hu|MMMEd|<Mt> <dn>., <Et>
hu|MMMEEEE|<Mt> <Et>
hu|MMMEEEEd|<Mt> <dn>., <Et>
hu|MMMM|<Mt>
hu|MMMMd|<Mt> <dn>.
hu|MMMME|<Et> (hónap: <Mt>)
hu|MMMMEd|<Mt> <dn>., <Et>
hu|MMMMEEEE|<Mt> <Et>
hu|MMMMEEEEd|<Mt> <dn>., <Et>
hu|y|<yn>.
hu|yd|<yn>. (nap: <dn>)
hu|yE|<yn>. <Et>
hu|yEd|<yn>. <dn>., <Et>
hu|yEEEE|<yn>. <Et>
hu|yEEEEd|<yn>. <dn>., <Et>
hu|yM|<yn>. <Mn>.
hu|yMd|<yn>. <MN>. <dN>.
hu|yME|<yn>. <Mn>. <Et>
hu|yMEd|<yn>. <MN>. <dN>., <Et>
hu|yMEEEE|<yn>. <Mn>. <Et>
hu|yMEEEEd|<yn>. <MN>. <dN>., <Et>
hu|yMM|<yn>. <MN>.
hu|yMMd|<yn>. <MN>. <dn>.
hu|yMME|<yn>. <MN>. <Et>
hu|yMMEd|<yn>. <MN>. <dN>., <Et>
hu|yMMEEEE|<yn>. <MN>. <Et>
hu|yMMEEEEd|<yn>. <MN>. <dN>., <Et>
hu|yMMM|<yn>. <Mt>
hu|yMMMd|<yn>. <Mt> <dn>.
hu|yMMME|<yn>. <Mt> <Et>
hu|yMMMEd|<yn>. <Mt> <dn>., <Et>
hu|yMMMEEEE|<yn>. <Mt> <Et>
hu|yMMMEEEEd|<yn>. <Mt> <dn>., <Et>
hu|yMMMM|<yn>. <Mt>
hu|yMMMMd|<yn>. <Mt> <dn>.
hu|yMMMME|<yn>. <Mt> <Et>
hu|yMMMMEd|<yn>. <Mt> <dn>., <Et>
hu|yMMMMEEEE|<yn>. <Mt> <Et>
hu|yMMMMEEEEd|<yn>. <Mt> <dn>., <Et>
hu|Gy|<Gt> <yn>.
hu|GyMMMd|<Gt> <yn>. <Mt> <dn>.
hu|GyMMMMEEEEd|<Gt> <yn>. <Mt> <dn>., <Et>
hu|GyMd|<Gt> <yn>. <MN>. <dN>.
hu|s|<sn>
hu|sz|<sn> <zt>
hu|szzzz|<sn> <zt>
hu|m|<mn>
hu|mz|<mn> <zt>
hu|mzzzz|<mn> <zt>
hu|ms|<mN>:<sN>
hu|msz|<mN>:<sN> <zt>
hu|mszzzz|<mN>:<sN> <zt>
hu|h|<at> <hn>
hu|hz|<at> <hn> <zt>
hu|hzzzz|<at> <hn> <zt>
hu|hs|<at> <hn> (másodperc: <sn>)
hu|hsz|<at> <hn> <zt> (másodperc: <sn>)
hu|hszzzz|<at> <hn> <zt> (másodperc: <sn>)
hu|hm|<at> <hn>:<mN>
hu|hmz|<at> <hn>:<mN> <zt>
hu|hmzzzz|<at> <hn>:<mN> <zt>
hu|hms|<at> <hn>:<mN>:<sN>
hu|hmsz|<at> <hn>:<mN>:<sN> <zt>
hu|hmszzzz|<at> <hn>:<mN>:<sN> <zt>
hu|H|<hn>
hu|Hz|<hN>h <zt>
hu|Hzzzz|<hN>h <zt>
hu|Hs|<hn> (másodperc: <sn>)
hu|Hsz|<hN>h <zt> (másodperc: <sn>)
hu|Hszzzz|<hN>h <zt> (másodperc: <sn>)
hu|Hm|<hn>:<mN>
hu|Hmz|<hN>:<mN> <zt>
hu|Hmzzzz|<hN>:<mN> <zt>
hu|Hms|<hn>:<mN>:<sN>
hu|Hmsz|<hn>:<mN>:<sN> <zt>
hu|Hmszzzz|<hn>:<mN>:<sN> <zt>
hu|yMMMdh|<yn>. <Mt> <dn>. <at> <hn>
hu|yMMMdhm|<yn>. <Mt> <dn>. <at> <hn>:<mN>
hu|yMMMdHm|<yn>. <Mt> <dn>. <hn>:<mN>
hu|yMMMdhmz|<yn>. <Mt> <dn>. <at> <hn>:<mN> <zt>
hu|yMMMdHms|<yn>. <Mt> <dn>. <hn>:<mN>:<sN>
hu|yMMMdHmzzzz|<yn>. <Mt> <dn>. <hN>:<mN> <zt>
hu|MMMEdh|<Mt> <dn>., <Et> <at> <hn>
hu|MMMEdhm|<Mt> <dn>., <Et> <at> <hn>:<mN>
hu|MMMEdHm|<Mt> <dn>., <Et> <hn>:<mN>
hu|MMMEdhmz|<Mt> <dn>., <Et> <at> <hn>:<mN> <zt>
hu|MMMEdHms|<Mt> <dn>., <Et> <hn>:<mN>:<sN>
hu|MMMEdHmzzzz|<Mt> <dn>., <Et> <hN>:<mN> <zt>
hu|yMdh|<yn>. <MN>. <dN>. <at> <hn>
hu|yMdhm|<yn>. <MN>. <dN>. <at> <hn>:<mN>
hu|yMdHm|<yn>. <MN>. <dN>. <hn>:<mN>
hu|yMdhmz|<yn>. <MN>. <dN>. <at> <hn>:<mN> <zt>
hu|yMdHms|<yn>. <MN>. <dN>. <hn>:<mN>:<sN>
hu|yMdHmzzzz|<yn>. <MN>. <dN>. <hN>:<mN> <zt>
hu|Eh|<Et> <at> <hn>
hu|Ehm|<Et> <hn>:<mN> <at>
hu|EHm|<Et> <hN>:<mN>
hu|Ehmz|<Et> <at> <hn>:<mN> <zt>
hu|EHms|<Et> <hN>:<mN>:<sN>
hu|EHmzzzz|<Et> <hN>:<mN> <zt>
hu|dh|<dn> <at> <hn>
hu|dhm|<dn> <at> <hn>:<mN>
hu|dHm|<dn> <hn>:<mN>
hu|dhmz|<dn> <at> <hn>:<mN> <zt>
hu|dHms|<dn> <hn>:<mN>:<sN>
hu|dHmzzzz|<dn> <hN>:<mN> <zt>
hu|MMMMdh|<Mt> <dn>. <at> <hn>
hu|MMMMdhm|<Mt> <dn>. <at> <hn>:<mN>
hu|MMMMdHm|<Mt> <dn>. <hn>:<mN>
hu|MMMMdhmz|<Mt> <dn>. <at> <hn>:<mN> <zt>
hu|MMMMdHms|<Mt> <dn>. <hn>:<mN>:<sN>
hu|MMMMdHmzzzz|<Mt> <dn>. <hN>:<mN> <zt>
hu|yMMMMEEEEdh|<yn>. <Mt> <dn>., <Et> <at> <hn>
hu|yMMMMEEEEdhm|<yn>. <Mt> <dn>., <Et> <at> <hn>:<mN>
hu|yMMMMEEEEdHm|<yn>. <Mt> <dn>., <Et> <hn>:<mN>
hu|yMMMMEEEEdhmz|<yn>. <Mt> <dn>., <Et> <at> <hn>:<mN> <zt>
hu|yMMMMEEEEdHms|<yn>. <Mt> <dn>., <Et> <hn>:<mN>:<sN>
hu|yMMMMEEEEdHmzzzz|<yn>. <Mt> <dn>., <Et> <hN>:<mN> <zt>
hu|EEEEdh|<dn>., <Et> <at> <hn>
hu|EEEEdhm|<dn>., <Et> <at> <hn>:<mN>
hu|EEEEdHm|<dn>., <Et> <hn>:<mN>
hu|EEEEdhmz|<dn>., <Et> <at> <hn>:<mN> <zt>
hu|EEEEdHms|<dn>., <Et> <hn>:<mN>:<sN>
hu|EEEEdHmzzzz|<dn>., <Et> <hN>:<mN> <zt>
hy|d|<dn>
hy|E|<Et>
hy|Ed|<dn>, <Et>
hy|EEEE|<Et>
hy|EEEEd|<dn>, <Et>
hy|M|<Mn>
hy|Md|<dN>.<MN>
hy|ME|<Mn> <Et>
hy|MEd|<dN>.<MN>, <Et>
hy|MEEEE|<Mn> <Et>
hy|MEEEEd|<dN>.<MN>, <Et>
hy|MM|<MN>
hy|MMd|<dN>.<MN>
hy|MME|<Et> (ամիս: <MN>)
hy|MMEd|<dN>.<MN>, <Et>
hy|MMEEEE|<MN> <Et>
hy|MMEEEEd|<dN>.<MN>, <Et>
hy|MMM|<Mt>
hy|MMMd|<dn> <Mt>
hy|MMME|<Mt> <Et>
hy|MMMEd|<Mt> <dn>, <Et>
hy|MMMEEEE|<Mt> <Et>
hy|MMMEEEEd|<Mt> <dn>, <Et>
hy|MMMM|<Mt>
hy|MMMMd|<Mt> <dn>
hy|MMMME|<Et> (ամիս: <Mt>)
hy|MMMMEd|<Mt> <dn>, <Et>
hy|MMMMEEEE|<Mt> <Et>
hy|MMMMEEEEd|<Mt> <dn>, <Et>
hy|y|<yn>
hy|yd|<yn> (օր: <dn>)
hy|yE|<yn> <Et>
hy|yEd|<yn> <dn>, <Et>
hy|yEEEE|<yn> <Et>
hy|yEEEEd|<yn> <dn>, <Et>
hy|yM|<MN>.<yn>
hy|yMd|<dN>.<MN>.<yn>
hy|yME|<MN>.<yn> <Et>
hy|yMEd|<dn>.<MN>.<yn>, <Et>
hy|yMEEEE|<MN>.<yn> <Et>
hy|yMEEEEd|<dn>.<MN>.<yn>, <Et>
hy|yMM|<MN>.<yn>
hy|yMMd|<dN>.<MN>.<yn>
hy|yMME|<MN>.<yn> <Et>
hy|yMMEd|<dn>.<MN>.<yn>, <Et>
hy|yMMEEEE|<MN>.<yn> <Et>
hy|yMMEEEEd|<dn>.<MN>.<yn>, <Et>
hy|yMMM|<yn> թ. <Mt>
hy|yMMMd|<dn> <Mt>, <yn> թ.
hy|yMMME|<yn> թ. <Mt> <Et>
hy|yMMMEd|<yn> թ. <Mt> <dn>, <Et>
hy|yMMMEEEE|<yn> թ. <Mt> <Et>
hy|yMMMEEEEd|<yn> թ. <Mt> <dn>, <Et>
hy|yMMMM|<yn> թ․ <Mt>
hy|yMMMMd|<dn> <Mt>, <yn> թ.
hy|yMMMME|<yn> թ․ <Mt> <Et>
hy|yMMMMEd|<yn> թ. <Mt> <dn>, <Et>
hy|yMMMMEEEE|<yn> թ․ <Mt> <Et>
hy|yMMMMEEEEd|<yn> թ. <Mt> <dn>, <Et>
hy|Gy|<Gt> <yn> թ.
hy|GyMMMd|<Gt> <yn> թ․ <Mt> <dn>
hy|GyMMMMEEEEd|<Gt> <yn> թ. <Mt> <dn>, <Et>
hy|GyMd|<Gt> <dN>.<MN>.<yn>
hy|s|<sn>
hy|sz|<sn> <zt>
hy|szzzz|<sn> <zt>
hy|m|<mn>
hy|mz|<mn> <zt>
hy|mzzzz|<mn> <zt>
hy|ms|<mN>:<sN>
hy|msz|<mN>:<sN> <zt>
hy|mszzzz|<mN>:<sN> <zt>
hy|h|<hn> <at>
hy|hz|<hn> <at> <zt>
hy|hzzzz|<hn> <at> <zt>
hy|hs|<hn> <at> (վայրկյան: <sn>)
hy|hsz|<hn> <at> <zt> (վայրկյան: <sn>)
hy|hszzzz|<hn> <at> <zt> (վայրկյան: <sn>)
hy|hm|<hn>:<mN> <at>
hy|hmz|<hn>:<mN> <at> <zt>
hy|hmzzzz|<hn>:<mN> <at> <zt>
hy|hms|<hn>:<mN>:<sN> <at>
hy|hmsz|<hn>:<mN>:<sN> <at> <zt>
hy|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
hy|H|<hn>
hy|Hz|ժ․ <hN> <zt>
hy|Hzzzz|ժ․ <hN> <zt>
hy|Hs|<hn> (վայրկյան: <sn>)
hy|Hsz|ժ․ <hN> <zt> (վայրկյան: <sn>)
hy|Hszzzz|ժ․ <hN> <zt> (վայրկյան: <sn>)
hy|Hm|<hn>:<mN>
hy|Hmz|<hN>:<mN> <zt>
hy|Hmzzzz|<hN>:<mN> <zt>
hy|Hms|<hn>:<mN>:<sN>
hy|Hmsz|<hn>:<mN>:<sN> <zt>
hy|Hmszzzz|<hn>:<mN>:<sN> <zt>
hy|yMMMdh|<dn> <Mt>, <yn> թ., <hn> <at>
hy|yMMMdhm|<dn> <Mt>, <yn> թ., <hn>:<mN> <at>
hy|yMMMdHm|<dn> <Mt>, <yn> թ., <hn>:<mN>
hy|yMMMdhmz|<dn> <Mt>, <yn> թ., <hn>:<mN> <at> <zt>
hy|yMMMdHms|<dn> <Mt>, <yn> թ., <hn>:<mN>:<sN>
hy|yMMMdHmzzzz|<dn> <Mt>, <yn> թ., <hN>:<mN> <zt>
hy|MMMEdh|<Mt> <dn>, <Et>, <hn> <at>
hy|MMMEdhm|<Mt> <dn>, <Et>, <hn>:<mN> <at>
hy|MMMEdHm|<Mt> <dn>, <Et>, <hn>:<mN>
hy|MMMEdhmz|<Mt> <dn>, <Et>, <hn>:<mN> <at> <zt>
hy|MMMEdHms|<Mt> <dn>, <Et>, <hn>:<mN>:<sN>
hy|MMMEdHmzzzz|<Mt> <dn>, <Et>, <hN>:<mN> <zt>
hy|yMdh|<dN>.<MN>.<yn>, <hn> <at>
hy|yMdhm|<dN>.<MN>.<yn>, <hn>:<mN> <at>
hy|yMdHm|<dN>.<MN>.<yn>, <hn>:<mN>
hy|yMdhmz|<dN>.<MN>.<yn>, <hn>:<mN> <at> <zt>
hy|yMdHms|<dN>.<MN>.<yn>, <hn>:<mN>:<sN>
hy|yMdHmzzzz|<dN>.<MN>.<yn>, <hN>:<mN> <zt>
hy|Eh|<Et> <hn> <at>
hy|Ehm|<Et>, <hn>:<mN> <at>
hy|EHm|<Et>, <hN>:<mN>
hy|Ehmz|<Et>, <hn>:<mN> <at> <zt>
hy|EHms|<Et>, <hN>:<mN>:<sN>
hy|EHmzzzz|<Et>, <hN>:<mN> <zt>
hy|dh|<dn>, <hn> <at>
hy|dhm|<dn>, <hn>:<mN> <at>
hy|dHm|<dn>, <hn>:<mN>
hy|dhmz|<dn>, <hn>:<mN> <at> <zt>
hy|dHms|<dn>, <hn>:<mN>:<sN>
hy|dHmzzzz|<dn>, <hN>:<mN> <zt>
hy|MMMMdh|<Mt> <dn>, <hn> <at>
hy|MMMMdhm|<Mt> <dn>, <hn>:<mN> <at>
hy|MMMMdHm|<Mt> <dn>, <hn>:<mN>
hy|MMMMdhmz|<Mt> <dn>, <hn>:<mN> <at> <zt>
hy|MMMMdHms|<Mt> <dn>, <hn>:<mN>:<sN>
hy|MMMMdHmzzzz|<Mt> <dn>, <hN>:<mN> <zt>
hy|yMMMMEEEEdh|<yn> թ. <Mt> <dn>, <Et>, <hn> <at>
hy|yMMMMEEEEdhm|<yn> թ. <Mt> <dn>, <Et>, <hn>:<mN> <at>
hy|yMMMMEEEEdHm|<yn> թ. <Mt> <dn>, <Et>, <hn>:<mN>
hy|yMMMMEEEEdhmz|<yn> թ. <Mt> <dn>, <Et>, <hn>:<mN> <at> <zt>
hy|yMMMMEEEEdHms|<yn> թ. <Mt> <dn>, <Et>, <hn>:<mN>:<sN>
hy|yMMMMEEEEdHmzzzz|<yn> թ. <Mt> <dn>, <Et>, <hN>:<mN> <zt>
hy|EEEEdh|<dn>, <Et>, <hn> <at>
hy|EEEEdhm|<dn>, <Et>, <hn>:<mN> <at>
hy|EEEEdHm|<dn>, <Et>, <hn>:<mN>
hy|EEEEdhmz|<dn>, <Et>, <hn>:<mN> <at> <zt>
hy|EEEEdHms|<dn>, <Et>, <hn>:<mN>:<sN>
hy|EEEEdHmzzzz|<dn>, <Et>, <hN>:<mN> <zt>
id|d|<dn>
id|E|<Et>
id|Ed|<Et>, <dn>
id|EEEE|<Et>
id|EEEEd|<Et>, <dn>
id|M|<Mn>
id|Md|<dn>/<Mn>
id|ME|<Mn> <Et>
id|MEd|<Et>, <dn>/<Mn>
id|MEEEE|<Mn> <Et>
id|MEEEEd|<Et>, <dn>/<Mn>
id|MM|<MN>
id|MMd|<dn>/<MN>
id|MME|<Et> (bulan: <MN>)
id|MMEd|<Et>, <dn>/<MN>
id|MMEEEE|<MN> <Et>
id|MMEEEEd|<Et>, <dn>/<MN>
id|MMM|<Mt>
id|MMMd|<dn> <Mt>
id|MMME|<Mt> <Et>
id|MMMEd|<Et>, <dn> <Mt>
id|MMMEEEE|<Mt> <Et>
id|MMMEEEEd|<Et>, <dn> <Mt>
id|MMMM|<Mt>
id|MMMMd|<dn> <Mt>
id|MMMME|<Et> (bulan: <Mt>)
id|MMMMEd|<Et>, <dn> <Mt>
id|MMMMEEEE|<Mt> <Et>
id|MMMMEEEEd|<Et>, <dn> <Mt>
id|y|<yn>
id|yd|<yn> (hari: <dn>)
id|yE|<yn> <Et>
id|yEd|<yn> <Et>, <dn>
id|yEEEE|<yn> <Et>
id|yEEEEd|<yn> <Et>, <dn>
id|yM|<Mn>/<yn>
id|yMd|<dn>/<Mn>/<yn>
id|yME|<Mn>/<yn> <Et>
id|yMEd|<Et>, <dn>/<Mn>/<yn>
id|yMEEEE|<Mn>/<yn> <Et>
id|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
id|yMM|<MN>/<yn>
id|yMMd|<dn>/<MN>/<yn>
id|yMME|<MN>/<yn> <Et>
id|yMMEd|<Et>, <dn>/<MN>/<yn>
id|yMMEEEE|<MN>/<yn> <Et>
id|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
id|yMMM|<Mt> <yn>
id|yMMMd|<dn> <Mt> <yn>
id|yMMME|<Mt> <yn> <Et>
id|yMMMEd|<Et>, <dn> <Mt> <yn>
id|yMMMEEEE|<Mt> <yn> <Et>
id|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
id|yMMMM|<Mt> <yn>
id|yMMMMd|<dn> <Mt> <yn>
id|yMMMME|<Mt> <yn> <Et>
id|yMMMMEd|<Et>, <dn> <Mt> <yn>
id|yMMMMEEEE|<Mt> <yn> <Et>
id|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
id|Gy|<yn> <Gt>
id|GyMMMd|<dn> <Mt> <yn> <Gt>
id|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
id|GyMd|<dn>/<Mn>/<yn> <Gt>
id|s|<sn>
id|sz|<sn> <zt>
id|szzzz|<sn> <zt>
id|m|<mn>
id|mz|<mn> <zt>
id|mzzzz|<mn> <zt>
id|ms|<mN>.<sN>
id|msz|<mN>.<sN> <zt>
id|mszzzz|<mN>.<sN> <zt>
id|h|<hn> <at>
id|hz|<hn> <at> <zt>
id|hzzzz|<hn> <at> <zt>
id|hs|<hn> <at> (detik: <sn>)
id|hsz|<hn> <at> <zt> (detik: <sn>)
id|hszzzz|<hn> <at> <zt> (detik: <sn>)
id|hm|<hn>.<mN> <at>
id|hmz|<hn>.<mN> <at> <zt>
id|hmzzzz|<hn>.<mN> <at> <zt>
id|hms|<hn>.<mN>.<sN> <at>
id|hmsz|<hn>.<mN>.<sN>. <at> <zt>
id|hmszzzz|<hn>.<mN>.<sN>. <at> <zt>
id|H|<hN>
id|Hz|<hN>h <zt>
id|Hzzzz|<hN>h <zt>
id|Hs|<hN> (detik: <sn>)
id|Hsz|<hN>h <zt> (detik: <sn>)
id|Hszzzz|<hN>h <zt> (detik: <sn>)
id|Hm|<hN>.<mN>
id|Hmz|<hN>.<mN> <zt>
id|Hmzzzz|<hN>.<mN> <zt>
id|Hms|<hN>.<mN>.<sN>
id|Hmsz|<hn>.<mN>.<sN> <zt>
id|Hmszzzz|<hn>.<mN>.<sN> <zt>
id|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
id|yMMMdhm|<dn> <Mt> <yn>, <hn>.<mN> <at>
id|yMMMdHm|<dn> <Mt> <yn>, <hN>.<mN>
id|yMMMdhmz|<dn> <Mt> <yn>, <hn>.<mN> <at> <zt>
id|yMMMdHms|<dn> <Mt> <yn>, <hN>.<mN>.<sN>
id|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>.<mN> <zt>
id|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
id|MMMEdhm|<Et>, <dn> <Mt>, <hn>.<mN> <at>
id|MMMEdHm|<Et>, <dn> <Mt>, <hN>.<mN>
id|MMMEdhmz|<Et>, <dn> <Mt>, <hn>.<mN> <at> <zt>
id|MMMEdHms|<Et>, <dn> <Mt>, <hN>.<mN>.<sN>
id|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>.<mN> <zt>
id|yMdh|<dn>/<Mn>/<yn>, <hn> <at>
id|yMdhm|<dn>/<Mn>/<yn>, <hn>.<mN> <at>
id|yMdHm|<dn>/<Mn>/<yn>, <hN>.<mN>
id|yMdhmz|<dn>/<Mn>/<yn>, <hn>.<mN> <at> <zt>
id|yMdHms|<dn>/<Mn>/<yn>, <hN>.<mN>.<sN>
id|yMdHmzzzz|<dn>/<Mn>/<yn>, <hN>.<mN> <zt>
id|Eh|<Et> <hn> <at>
id|Ehm|<Et> <hn>.<mN> <at>
id|EHm|<Et> <hN>.<mN>
id|Ehmz|<Et>, <hn>.<mN> <at> <zt>
id|EHms|<Et> <hN>.<mN>.<sN>
id|EHmzzzz|<Et>, <hN>.<mN> <zt>
id|dh|<dn>, <hn> <at>
id|dhm|<dn>, <hn>.<mN> <at>
id|dHm|<dn>, <hN>.<mN>
id|dhmz|<dn>, <hn>.<mN> <at> <zt>
id|dHms|<dn>, <hN>.<mN>.<sN>
id|dHmzzzz|<dn>, <hN>.<mN> <zt>
id|MMMMdh|<dn> <Mt> pukul <hn> <at>
id|MMMMdhm|<dn> <Mt> pukul <hn>.<mN> <at>
id|MMMMdHm|<dn> <Mt> pukul <hN>.<mN>
id|MMMMdhmz|<dn> <Mt> pukul <hn>.<mN> <at> <zt>
id|MMMMdHms|<dn> <Mt> pukul <hN>.<mN>.<sN>
id|MMMMdHmzzzz|<dn> <Mt> pukul <hN>.<mN> <zt>
id|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> pukul <hn> <at>
id|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> pukul <hn>.<mN> <at>
id|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> pukul <hN>.<mN>
id|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> pukul <hn>.<mN> <at> <zt>
id|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> pukul <hN>.<mN>.<sN>
id|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> pukul <hN>.<mN> <zt>
id|EEEEdh|<Et>, <dn>, <hn> <at>
id|EEEEdhm|<Et>, <dn>, <hn>.<mN> <at>
id|EEEEdHm|<Et>, <dn>, <hN>.<mN>
id|EEEEdhmz|<Et>, <dn>, <hn>.<mN> <at> <zt>
id|EEEEdHms|<Et>, <dn>, <hN>.<mN>.<sN>
id|EEEEdHmzzzz|<Et>, <dn>, <hN>.<mN> <zt>
is|d|<dn>
is|E|<Et>
is|Ed|<Et> <dn>.
is|EEEE|<Et>
is|EEEEd|<Et> <dn>.
is|M|<Mn>
is|Md|<dn>.<Mn>.
is|ME|<Mn> <Et>
is|MEd|<Et>, <dn>.<Mn>.
is|MEEEE|<Mn> <Et>
is|MEEEEd|<Et>, <dn>.<Mn>.
is|MM|<MN>
is|MMd|<dn>.<MN>.
is|MME|<Et> (mánuður: <MN>)
is|MMEd|<Et>, <dn>.<MN>.
is|MMEEEE|<MN> <Et>
is|MMEEEEd|<Et>, <dn>.<MN>.
is|MMM|<Mt>
is|MMMd|<dn>. <Mt>
is|MMME|<Mt> <Et>
is|MMMEd|<Et>, <dn>. <Mt>
is|MMMEEEE|<Mt> <Et>
is|MMMEEEEd|<Et>, <dn>. <Mt>
is|MMMM|<Mt>
is|MMMMd|<dn>. <Mt>
is|MMMME|<Et> (mánuður: <Mt>)
is|MMMMEd|<Et>, <dn>. <Mt>
is|MMMMEEEE|<Mt> <Et>
is|MMMMEEEEd|<Et>, <dn>. <Mt>
is|y|<yn>
is|yd|<yn> (dagur: <dn>)
is|yE|<yn> <Et>
is|yEd|<yn> <Et> <dn>.
is|yEEEE|<yn> <Et>
is|yEEEEd|<yn> <Et> <dn>.
is|yM|<Mn>. <yn>
is|yMd|<dn>.<Mn>.<yn>
is|yME|<Mn>. <yn> <Et>
is|yMEd|<Et>, <dn>.<Mn>.<yn>
is|yMEEEE|<Mn>. <yn> <Et>
is|yMEEEEd|<Et>, <dn>.<Mn>.<yn>
is|yMM|<MN>. <yn>
is|yMMd|<dn>.<MN>.<yn>
is|yMME|<MN>. <yn> <Et>
is|yMMEd|<Et>, <dn>.<MN>.<yn>
is|yMMEEEE|<MN>. <yn> <Et>
is|yMMEEEEd|<Et>, <dn>.<MN>.<yn>
is|yMMM|<Mt> <yn>
is|yMMMd|<dn>. <Mt> <yn>
is|yMMME|<Mt> <yn> <Et>
is|yMMMEd|<Et>, <dn>. <Mt> <yn>
is|yMMMEEEE|<Mt> <yn> <Et>
is|yMMMEEEEd|<Et>, <dn>. <Mt> <yn>
is|yMMMM|<Mt> <yn>
is|yMMMMd|<dn>. <Mt> <yn>
is|yMMMME|<Mt> <yn> <Et>
is|yMMMMEd|<Et>, <dn>. <Mt> <yn>
is|yMMMMEEEE|<Mt> <yn> <Et>
is|yMMMMEEEEd|<Et>, <dn>. <Mt> <yn>
is|Gy|<yn> <Gt>
is|GyMMMd|<dn>. <Mt> <yn> <Gt>
is|GyMMMMEEEEd|<Et>, <dn>. <Mt> <yn> <Gt>
is|GyMd|<dn>/<Mn>/<yn> <Gt>
is|s|<sn>
is|sz|<sn> <zt>
is|szzzz|<sn> <zt>
is|m|<mn>
is|mz|<mn> <zt>
is|mzzzz|<mn> <zt>
is|ms|<mN>:<sN>
is|msz|<mN>:<sN> <zt>
is|mszzzz|<mN>:<sN> <zt>
is|h|<hn> <at>
is|hz|<hn> <at> <zt>
is|hzzzz|<hn> <at> <zt>
is|hs|<hn> <at> (sekúnda: <sn>)
is|hsz|<hn> <at> <zt> (sekúnda: <sn>)
is|hszzzz|<hn> <at> <zt> (sekúnda: <sn>)
is|hm|<hn>:<mN> <at>
is|hmz|<hn>:<mN> <at> <zt>
is|hmzzzz|<hn>:<mN> <at> <zt>
is|hms|<hn>:<mN>:<sN> <at>
is|hmsz|<hn>:<mN>:<sN> <at> <zt>
is|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
is|H|<hN>
is|Hz|<hN>klst. <zt>
is|Hzzzz|<hN>klst. <zt>
is|Hs|<hN> (sekúnda: <sn>)
is|Hsz|<hN>klst. <zt> (sekúnda: <sn>)
is|Hszzzz|<hN>klst. <zt> (sekúnda: <sn>)
is|Hm|<hN>:<mN>
is|Hmz|<zt> – <hN>:<mN>
is|Hmzzzz|<zt> – <hN>:<mN>
is|Hms|<hN>:<mN>:<sN>
is|Hmsz|<hn>:<mN>:<sN> <zt>
is|Hmszzzz|<hn>:<mN>:<sN> <zt>
is|yMMMdh|<dn>. <Mt> <yn>, <hn> <at>
is|yMMMdhm|<dn>. <Mt> <yn>, <hn>:<mN> <at>
is|yMMMdHm|<dn>. <Mt> <yn>, <hN>:<mN>
is|yMMMdhmz|<dn>. <Mt> <yn>, <hn>:<mN> <at> <zt>
is|yMMMdHms|<dn>. <Mt> <yn>, <hN>:<mN>:<sN>
is|yMMMdHmzzzz|<dn>. <Mt> <yn>, <zt> – <hN>:<mN>
is|MMMEdh|<Et>, <dn>. <Mt>, <hn> <at>
is|MMMEdhm|<Et>, <dn>. <Mt>, <hn>:<mN> <at>
is|MMMEdHm|<Et>, <dn>. <Mt>, <hN>:<mN>
is|MMMEdhmz|<Et>, <dn>. <Mt>, <hn>:<mN> <at> <zt>
is|MMMEdHms|<Et>, <dn>. <Mt>, <hN>:<mN>:<sN>
is|MMMEdHmzzzz|<Et>, <dn>. <Mt>, <zt> – <hN>:<mN>
is|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
is|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
is|yMdHm|<dn>.<Mn>.<yn>, <hN>:<mN>
is|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at> <zt>
is|yMdHms|<dn>.<Mn>.<yn>, <hN>:<mN>:<sN>
is|yMdHmzzzz|<dn>.<Mn>.<yn>, <zt> – <hN>:<mN>
is|Eh|<Et> <hn> <at>
is|Ehm|<Et>, <hn>:<mN> <at>
is|EHm|<Et>, <hN>:<mN>
is|Ehmz|<Et>, <hn>:<mN> <at> <zt>
is|EHms|<Et>, <hN>:<mN>:<sN>
is|EHmzzzz|<Et>, <zt> – <hN>:<mN>
is|dh|<dn>, <hn> <at>
is|dhm|<dn>, <hn>:<mN> <at>
is|dHm|<dn>, <hN>:<mN>
is|dhmz|<dn>, <hn>:<mN> <at> <zt>
is|dHms|<dn>, <hN>:<mN>:<sN>
is|dHmzzzz|<dn>, <zt> – <hN>:<mN>
is|MMMMdh|<dn>. <Mt> kl. <hn> <at>
is|MMMMdhm|<dn>. <Mt> kl. <hn>:<mN> <at>
is|MMMMdHm|<dn>. <Mt> kl. <hN>:<mN>
is|MMMMdhmz|<dn>. <Mt> kl. <hn>:<mN> <at> <zt>
is|MMMMdHms|<dn>. <Mt> kl. <hN>:<mN>:<sN>
is|MMMMdHmzzzz|<dn>. <Mt> kl. <zt> – <hN>:<mN>
is|yMMMMEEEEdh|<Et>, <dn>. <Mt> <yn> kl. <hn> <at>
is|yMMMMEEEEdhm|<Et>, <dn>. <Mt> <yn> kl. <hn>:<mN> <at>
is|yMMMMEEEEdHm|<Et>, <dn>. <Mt> <yn> kl. <hN>:<mN>
is|yMMMMEEEEdhmz|<Et>, <dn>. <Mt> <yn> kl. <hn>:<mN> <at> <zt>
is|yMMMMEEEEdHms|<Et>, <dn>. <Mt> <yn> kl. <hN>:<mN>:<sN>
is|yMMMMEEEEdHmzzzz|<Et>, <dn>. <Mt> <yn> kl. <zt> – <hN>:<mN>
is|EEEEdh|<Et> <dn>., <hn> <at>
is|EEEEdhm|<Et> <dn>., <hn>:<mN> <at>
is|EEEEdHm|<Et> <dn>., <hN>:<mN>
is|EEEEdhmz|<Et> <dn>., <hn>:<mN> <at> <zt>
is|EEEEdHms|<Et> <dn>., <hN>:<mN>:<sN>
is|EEEEdHmzzzz|<Et> <dn>., <zt> – <hN>:<mN>
it|d|<dn>
it|E|<Et>
it|Ed|<Et> <dn>
it|EEEE|<Et>
it|EEEEd|<Et> <dn>
it|M|<Mn>
it|Md|<dN>/<MN>
it|ME|<Mn> <Et>
it|MEd|<Et> <dN>/<MN>
it|MEEEE|<Mn> <Et>
it|MEEEEd|<Et> <dN>/<MN>
it|MM|<MN>
it|MMd|<dN>/<MN>
it|MME|<Et> (mese: <MN>)
it|MMEd|<Et> <dN>/<MN>
it|MMEEEE|<MN> <Et>
it|MMEEEEd|<Et> <dN>/<MN>
it|MMM|<Mt>
it|MMMd|<dn> <Mt>
it|MMME|<Mt> <Et>
it|MMMEd|<Et> <dn> <Mt>
it|MMMEEEE|<Mt> <Et>
it|MMMEEEEd|<Et> <dn> <Mt>
it|MMMM|<Mt>
it|MMMMd|<dn> <Mt>
it|MMMME|<Et> (mese: <Mt>)
it|MMMMEd|<Et> <dn> <Mt>
it|MMMMEEEE|<Mt> <Et>
it|MMMMEEEEd|<Et> <dn> <Mt>
it|y|<yn>
it|yd|<yn> (giorno: <dn>)
it|yE|<yn> <Et>
it|yEd|<yn> <Et> <dn>
it|yEEEE|<yn> <Et>
it|yEEEEd|<yn> <Et> <dn>
it|yM|<MN>/<yn>
it|yMd|<dN>/<MN>/<yn>
it|yME|<MN>/<yn> <Et>
it|yMEd|<Et> <dN>/<MN>/<yn>
it|yMEEEE|<MN>/<yn> <Et>
it|yMEEEEd|<Et> <dN>/<MN>/<yn>
it|yMM|<MN>/<yn>
it|yMMd|<dN>/<MN>/<yn>
it|yMME|<MN>/<yn> <Et>
it|yMMEd|<Et> <dN>/<MN>/<yn>
it|yMMEEEE|<MN>/<yn> <Et>
it|yMMEEEEd|<Et> <dN>/<MN>/<yn>
it|yMMM|<Mt> <yn>
it|yMMMd|<dn> <Mt> <yn>
it|yMMME|<Mt> <yn> <Et>
it|yMMMEd|<Et> <dn> <Mt> <yn>
it|yMMMEEEE|<Mt> <yn> <Et>
it|yMMMEEEEd|<Et> <dn> <Mt> <yn>
it|yMMMM|<Mt> <yn>
it|yMMMMd|<dn> <Mt> <yn>
it|yMMMME|<Mt> <yn> <Et>
it|yMMMMEd|<Et> <dn> <Mt> <yn>
it|yMMMMEEEE|<Mt> <yn> <Et>
it|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
it|Gy|<yn> <Gt>
it|GyMMMd|<dn> <Mt> <yn> <Gt>
it|GyMMMMEEEEd|<Et> <dn> <Mt> <yn> <Gt>
it|GyMd|<dN>/<MN>/<yn> <Gt>
it|s|<sn>
it|sz|<sn> <zt>
it|szzzz|<sn> <zt>
it|m|<mn>
it|mz|<mn> <zt>
it|mzzzz|<mn> <zt>
it|ms|<mN>:<sN>
it|msz|<mN>:<sN> <zt>
it|mszzzz|<mN>:<sN> <zt>
it|h|<hn> <at>
it|hz|<hn> <at> <zt>
it|hzzzz|<hn> <at> <zt>
it|hs|<hn> <at> (secondo: <sn>)
it|hsz|<hn> <at> <zt> (secondo: <sn>)
it|hszzzz|<hn> <at> <zt> (secondo: <sn>)
it|hm|<hn>:<mN> <at>
it|hmz|<hn>:<mN> <at> <zt>
it|hmzzzz|<hn>:<mN> <at> <zt>
it|hms|<hn>:<mN>:<sN> <at>
it|hmsz|<hn>:<mN>:<sN> <at> <zt>
it|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
it|H|<hn>
it|Hz|h<hN> <zt>
it|Hzzzz|h<hN> <zt>
it|Hs|<hn> (secondo: <sn>)
it|Hsz|h<hN> <zt> (secondo: <sn>)
it|Hszzzz|h<hN> <zt> (secondo: <sn>)
it|Hm|<hN>:<mN>
it|Hmz|<hN>:<mN> <zt>
it|Hmzzzz|<hN>:<mN> <zt>
it|Hms|<hN>:<mN>:<sN>
it|Hmsz|<hn>:<mN>:<sN> <zt>
it|Hmszzzz|<hn>:<mN>:<sN> <zt>
it|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
it|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
it|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
it|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
it|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
it|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
it|MMMEdh|<Et> <dn> <Mt>, <hn> <at>
it|MMMEdhm|<Et> <dn> <Mt>, <hn>:<mN> <at>
it|MMMEdHm|<Et> <dn> <Mt>, <hN>:<mN>
it|MMMEdhmz|<Et> <dn> <Mt>, <hn>:<mN> <at> <zt>
it|MMMEdHms|<Et> <dn> <Mt>, <hN>:<mN>:<sN>
it|MMMEdHmzzzz|<Et> <dn> <Mt>, <hN>:<mN> <zt>
it|yMdh|<dN>/<MN>/<yn>, <hn> <at>
it|yMdhm|<dN>/<MN>/<yn>, <hn>:<mN> <at>
it|yMdHm|<dN>/<MN>/<yn>, <hN>:<mN>
it|yMdhmz|<dN>/<MN>/<yn>, <hn>:<mN> <at> <zt>
it|yMdHms|<dN>/<MN>/<yn>, <hN>:<mN>:<sN>
it|yMdHmzzzz|<dN>/<MN>/<yn>, <hN>:<mN> <zt>
it|Eh|<Et> <hn> <at>
it|Ehm|<Et> <hn>:<mN> <at>
it|EHm|<Et> <hN>:<mN>
it|Ehmz|<Et>, <hn>:<mN> <at> <zt>
it|EHms|<Et> <hN>:<mN>:<sN>
it|EHmzzzz|<Et>, <hN>:<mN> <zt>
it|dh|<dn>, <hn> <at>
it|dhm|<dn>, <hn>:<mN> <at>
it|dHm|<dn>, <hN>:<mN>
it|dhmz|<dn>, <hn>:<mN> <at> <zt>
it|dHms|<dn>, <hN>:<mN>:<sN>
it|dHmzzzz|<dn>, <hN>:<mN> <zt>
it|MMMMdh|<dn> <Mt> alle ore <hn> <at>
it|MMMMdhm|<dn> <Mt> alle ore <hn>:<mN> <at>
it|MMMMdHm|<dn> <Mt> alle ore <hN>:<mN>
it|MMMMdhmz|<dn> <Mt> alle ore <hn>:<mN> <at> <zt>
it|MMMMdHms|<dn> <Mt> alle ore <hN>:<mN>:<sN>
it|MMMMdHmzzzz|<dn> <Mt> alle ore <hN>:<mN> <zt>
it|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> alle ore <hn> <at>
it|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> alle ore <hn>:<mN> <at>
it|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> alle ore <hN>:<mN>
it|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> alle ore <hn>:<mN> <at> <zt>
it|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> alle ore <hN>:<mN>:<sN>
it|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> alle ore <hN>:<mN> <zt>
it|EEEEdh|<Et> <dn>, <hn> <at>
it|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
it|EEEEdHm|<Et> <dn>, <hN>:<mN>
it|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
it|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
it|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
ja|d|<dn>日
ja|E|<Et>
ja|Ed|<dn>日(<Et>)
ja|EEEE|<Et>
ja|EEEEd|<dn>日<Et>
ja|M|<Mn>月
ja|Md|<Mn>/<dn>
ja|ME|<Mn>月 <Et>
ja|MEd|<Mn>/<dn>(<Et>)
ja|MEEEE|<Mn>月 <Et>
ja|MEEEEd|<Mn>/<dn><Et>
ja|MM|<MN>月
ja|MMd|<MN>/<dn>
ja|MME|<Et> (月: <MN>月)
ja|MMEd|<MN>/<dn>(<Et>)
ja|MMEEEE|<MN>月 <Et>
ja|MMEEEEd|<MN>/<dn><Et>
ja|MMM|<Mn>月
ja|MMMd|<Mn>月<dn>日
ja|MMME|<Mn>月 <Et>
ja|MMMEd|<Mn>月<dn>日(<Et>)
ja|MMMEEEE|<Mn>月 <Et>
ja|MMMEEEEd|<Mn>月<dn>日<Et>
ja|MMMM|<Mn>月
ja|MMMMd|<Mn>月<dn>日
ja|MMMME|<Et> (月: <Mn>月)
ja|MMMMEd|<Mn>月<dn>日(<Et>)
ja|MMMMEEEE|<Mn>月 <Et>
ja|MMMMEEEEd|<Mn>月<dn>日<Et>
ja|y|<yn>年
ja|yd|<yn>年 (日: <dn>日)
ja|yE|<yn>年 <Et>
ja|yEd|<yn>年 <dn>日(<Et>)
ja|yEEEE|<yn>年 <Et>
ja|yEEEEd|<yn>年 <dn>日<Et>
ja|yM|<yn>/<Mn>
ja|yMd|<yn>/<Mn>/<dn>
ja|yME|<yn>/<Mn> <Et>
ja|yMEd|<yn>/<Mn>/<dn>(<Et>)
ja|yMEEEE|<yn>/<Mn> <Et>
ja|yMEEEEd|<yn>/<Mn>/<dn><Et>
ja|yMM|<yn>/<MN>
ja|yMMd|<yn>/<MN>/<dn>
ja|yMME|<yn>/<MN> <Et>
ja|yMMEd|<yn>/<MN>/<dn>(<Et>)
ja|yMMEEEE|<yn>/<MN> <Et>
ja|yMMEEEEd|<yn>/<MN>/<dn><Et>
ja|yMMM|<yn>年<Mn>月
ja|yMMMd|<yn>年<Mn>月<dn>日
ja|yMMME|<yn>年<Mn>月 <Et>
ja|yMMMEd|<yn>年<Mn>月<dn>日(<Et>)
ja|yMMMEEEE|<yn>年<Mn>月 <Et>
ja|yMMMEEEEd|<yn>年<Mn>月<dn>日<Et>
ja|yMMMM|<yn>年<Mn>月
ja|yMMMMd|<yn>年<Mn>月<dn>日
ja|yMMMME|<yn>年<Mn>月 <Et>
ja|yMMMMEd|<yn>年<Mn>月<dn>日(<Et>)
ja|yMMMMEEEE|<yn>年<Mn>月 <Et>
ja|yMMMMEEEEd|<yn>年<Mn>月<dn>日<Et>
ja|Gy|<Gt><yn>年
ja|GyMMMd|<Gt><yn>年<Mn>月<dn>日
ja|GyMMMMEEEEd|<Gt><yn>年<Mn>月<dn>日<Et>
ja|GyMd|<Gt><yn>/<Mn>/<dn>
ja|s|<sn>
ja|sz|<sn> <zt>
ja|szzzz|<sn> <zt>
ja|m|<mn>
ja|mz|<mn> <zt>
ja|mzzzz|<mn> <zt>
ja|ms|<mN>:<sN>
ja|msz|<mN>:<sN> <zt>
ja|mszzzz|<mN>:<sN> <zt>
ja|h|<at><hn>時
ja|hz|<at><hn>時 <zt>
ja|hzzzz|<at><hn>時 <zt>
ja|hs|<at><hn>時 (秒: <sn>)
ja|hsz|<at><hn>時 <zt> (秒: <sn>)
ja|hszzzz|<at><hn>時 <zt> (秒: <sn>)
ja|hm|<at><hn>:<mN>
ja|hmz|<at><hn>:<mN> <zt>
ja|hmzzzz|<at><hn>:<mN> <zt>
ja|hms|<at><hn>:<mN>:<sN>
ja|hmsz|<at><hn>:<mN>:<sN> <zt>
ja|hmszzzz|<at><hn>:<mN>:<sN> <zt>
ja|H|<hn>時
ja|Hz|<hn>時 <zt>
ja|Hzzzz|<hn>時 <zt>
ja|Hs|<hn>時 (秒: <sn>)
ja|Hsz|<hn>時 <zt> (秒: <sn>)
ja|Hszzzz|<hn>時 <zt> (秒: <sn>)
ja|Hm|<hn>:<mN>
ja|Hmz|<hn>:<mN> <zt>
ja|Hmzzzz|<hn>:<mN> <zt>
ja|Hms|<hn>:<mN>:<sN>
ja|Hmsz|<hn>:<mN>:<sN> <zt>
ja|Hmszzzz|<hn>時<mN>分<sN>秒 <zt>
ja|yMMMdh|<yn>年<Mn>月<dn>日 <at><hn>時
ja|yMMMdhm|<yn>年<Mn>月<dn>日 <at><hn>:<mN>
ja|yMMMdHm|<yn>年<Mn>月<dn>日 <hn>:<mN>
ja|yMMMdhmz|<yn>年<Mn>月<dn>日 <at><hn>:<mN> <zt>
ja|yMMMdHms|<yn>年<Mn>月<dn>日 <hn>:<mN>:<sN>
ja|yMMMdHmzzzz|<yn>年<Mn>月<dn>日 <hn>:<mN> <zt>
ja|MMMEdh|<Mn>月<dn>日(<Et>) <at><hn>時
ja|MMMEdhm|<Mn>月<dn>日(<Et>) <at><hn>:<mN>
ja|MMMEdHm|<Mn>月<dn>日(<Et>) <hn>:<mN>
ja|MMMEdhmz|<Mn>月<dn>日(<Et>) <at><hn>:<mN> <zt>
ja|MMMEdHms|<Mn>月<dn>日(<Et>) <hn>:<mN>:<sN>
ja|MMMEdHmzzzz|<Mn>月<dn>日(<Et>) <hn>:<mN> <zt>
ja|yMdh|<yn>/<Mn>/<dn> <at><hn>時
ja|yMdhm|<yn>/<Mn>/<dn> <at><hn>:<mN>
ja|yMdHm|<yn>/<Mn>/<dn> <hn>:<mN>
ja|yMdhmz|<yn>/<Mn>/<dn> <at><hn>:<mN> <zt>
ja|yMdHms|<yn>/<Mn>/<dn> <hn>:<mN>:<sN>
ja|yMdHmzzzz|<yn>/<Mn>/<dn> <hn>:<mN> <zt>
ja|Eh|<at><hn>時 (<Et>)
ja|Ehm|<at><hn>:<mN> (<Et>)
ja|EHm|<hn>:<mN> (<Et>)
ja|Ehmz|<Et> <at><hn>:<mN> <zt>
ja|EHms|<hn>:<mN>:<sN> (<Et>)
ja|EHmzzzz|<Et> <hn>:<mN> <zt>
ja|dh|<dn>日 <at><hn>時
ja|dhm|<dn>日 <at><hn>:<mN>
ja|dHm|<dn>日 <hn>:<mN>
ja|dhmz|<dn>日 <at><hn>:<mN> <zt>
ja|dHms|<dn>日 <hn>:<mN>:<sN>
ja|dHmzzzz|<dn>日 <hn>:<mN> <zt>
ja|MMMMdh|<Mn>月<dn>日 <at><hn>時
ja|MMMMdhm|<Mn>月<dn>日 <at><hn>:<mN>
ja|MMMMdHm|<Mn>月<dn>日 <hn>:<mN>
ja|MMMMdhmz|<Mn>月<dn>日 <at><hn>:<mN> <zt>
ja|MMMMdHms|<Mn>月<dn>日 <hn>:<mN>:<sN>
ja|MMMMdHmzzzz|<Mn>月<dn>日 <hn>:<mN> <zt>
ja|yMMMMEEEEdh|<yn>年<Mn>月<dn>日<Et> <at><hn>時
ja|yMMMMEEEEdhm|<yn>年<Mn>月<dn>日<Et> <at><hn>:<mN>
ja|yMMMMEEEEdHm|<yn>年<Mn>月<dn>日<Et> <hn>:<mN>
ja|yMMMMEEEEdhmz|<yn>年<Mn>月<dn>日<Et> <at><hn>:<mN> <zt>
ja|yMMMMEEEEdHms|<yn>年<Mn>月<dn>日<Et> <hn>:<mN>:<sN>
ja|yMMMMEEEEdHmzzzz|<yn>年<Mn>月<dn>日<Et> <hn>:<mN> <zt>
ja|EEEEdh|<dn>日<Et> <at><hn>時
ja|EEEEdhm|<dn>日<Et> <at><hn>:<mN>
ja|EEEEdHm|<dn>日<Et> <hn>:<mN>
ja|EEEEdhmz|<dn>日<Et> <at><hn>:<mN> <zt>
ja|EEEEdHms|<dn>日<Et> <hn>:<mN>:<sN>
ja|EEEEdHmzzzz|<dn>日<Et> <hn>:<mN> <zt>
ka|d|<dn>
ka|E|<Et>
ka|Ed|<dn> <Et>
ka|EEEE|<Et>
ka|EEEEd|<dn> <Et>
ka|M|<Mn>
ka|Md|<dn>.<Mn>
ka|ME|<Mn> <Et>
ka|MEd|<Et>, <dn>.<Mn>
ka|MEEEE|<Mn> <Et>
ka|MEEEEd|<Et>, <dn>.<Mn>
ka|MM|<MN>
ka|MMd|<dn>.<MN>
ka|MME|<Et> (თვე: <MN>)
ka|MMEd|<Et>, <dn>.<MN>
ka|MMEEEE|<MN> <Et>
ka|MMEEEEd|<Et>, <dn>.<MN>
ka|MMM|<Mt>
ka|MMMd|<dn> <Mt>
ka|MMME|<Mt> <Et>
ka|MMMEd|<Et>, <dn> <Mt>
ka|MMMEEEE|<Mt> <Et>
ka|MMMEEEEd|<Et>, <dn> <Mt>
ka|MMMM|<Mt>
ka|MMMMd|<dn> <Mt>
ka|MMMME|<Et> (თვე: <Mt>)
ka|MMMMEd|<Et>, <dn> <Mt>
ka|MMMMEEEE|<Mt> <Et>
ka|MMMMEEEEd|<Et>, <dn> <Mt>
ka|y|<yn>
ka|yd|<yn> (დღე: <dn>)
ka|yE|<yn> <Et>
ka|yEd|<yn> <dn> <Et>
ka|yEEEE|<yn> <Et>
ka|yEEEEd|<yn> <dn> <Et>
ka|yM|<Mn>.<yn>
ka|yMd|<dn>.<Mn>.<yn>
ka|yME|<Mn>.<yn> <Et>
ka|yMEd|<Et>, <dn>.<Mn>.<yn>
ka|yMEEEE|<Mn>.<yn> <Et>
ka|yMEEEEd|<Et>, <dn>.<Mn>.<yn>
ka|yMM|<MN>.<yn>
ka|yMMd|<dn>.<MN>.<yn>
ka|yMME|<MN>.<yn> <Et>
ka|yMMEd|<Et>, <dn>.<MN>.<yn>
ka|yMMEEEE|<MN>.<yn> <Et>
ka|yMMEEEEd|<Et>, <dn>.<MN>.<yn>
ka|yMMM|<Mt>. <yn>
ka|yMMMd|<dn> <Mt>. <yn>
ka|yMMME|<Mt>. <yn> <Et>
ka|yMMMEd|<Et>, <dn> <Mt>. <yn>
ka|yMMMEEEE|<Mt>. <yn> <Et>
ka|yMMMEEEEd|<Et>, <dn> <Mt>. <yn>
ka|yMMMM|<Mt>, <yn>
ka|yMMMMd|<dn> <Mt>, <yn>
ka|yMMMME|<Mt>, <yn> <Et>
ka|yMMMMEd|<Et>, <dn> <Mt>. <yn>
ka|yMMMMEEEE|<Mt>, <yn> <Et>
ka|yMMMMEEEEd|<Et>, <dn> <Mt>, <yn>
ka|Gy|<yn> <Gt>
ka|GyMMMd|<dn> <Mt>. <yn> <Gt>
ka|GyMMMMEEEEd|<Et>, <dn> <Mt>. <yn> <Gt>
ka|GyMd|<dn>.<Mn>.<yn> <Gt>
ka|s|<sn>
ka|sz|<sn> <zt>
ka|szzzz|<sn> <zt>
ka|m|<mn>
ka|mz|<mn> <zt>
ka|mzzzz|<mn> <zt>
ka|ms|<mN>:<sN>
ka|msz|<mN>:<sN> <zt>
ka|mszzzz|<mN>:<sN> <zt>
ka|h|<hn> <at>
ka|hz|<hn> <at> <zt>
ka|hzzzz|<hn> <at> <zt>
ka|hs|<hn> <at> (წამი: <sn>)
ka|hsz|<hn> <at> <zt> (წამი: <sn>)
ka|hszzzz|<hn> <at> <zt> (წამი: <sn>)
ka|hm|<hn>:<mN> <at>
ka|hmz|<hn>:<mN> <at> <zt>
ka|hmzzzz|<hn>:<mN> <at> <zt>
ka|hms|<hn>:<mN>:<sN> <at>
ka|hmsz|<hn>:<mN>:<sN> <at> <zt>
ka|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
ka|H|<hN>
ka|Hz|<hN>h <zt>
ka|Hzzzz|<hN>h <zt>
ka|Hs|<hN> (წამი: <sn>)
ka|Hsz|<hN>h <zt> (წამი: <sn>)
ka|Hszzzz|<hN>h <zt> (წამი: <sn>)
ka|Hm|<hN>:<mN>
ka|Hmz|<hN>:<mN> <zt>
ka|Hmzzzz|<hN>:<mN> <zt>
ka|Hms|<hN>:<mN>:<sN>
ka|Hmsz|<hn>:<mN>:<sN> <zt>
ka|Hmszzzz|<hn>:<mN>:<sN> <zt>
ka|yMMMdh|<dn> <Mt>. <yn>, <hn> <at>
ka|yMMMdhm|<dn> <Mt>. <yn>, <hn>:<mN> <at>
ka|yMMMdHm|<dn> <Mt>. <yn>, <hN>:<mN>
ka|yMMMdhmz|<dn> <Mt>. <yn>, <hn>:<mN> <at> <zt>
ka|yMMMdHms|<dn> <Mt>. <yn>, <hN>:<mN>:<sN>
ka|yMMMdHmzzzz|<dn> <Mt>. <yn>, <hN>:<mN> <zt>
ka|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
ka|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
ka|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
ka|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
ka|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
ka|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
ka|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
ka|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
ka|yMdHm|<dn>.<Mn>.<yn>, <hN>:<mN>
ka|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at> <zt>
ka|yMdHms|<dn>.<Mn>.<yn>, <hN>:<mN>:<sN>
ka|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>:<mN> <zt>
ka|Eh|<Et> <hn> <at>
ka|Ehm|<Et>, <hn>:<mN> <at>
ka|EHm|<Et>, <hN>:<mN>
ka|Ehmz|<Et>, <hn>:<mN> <at> <zt>
ka|EHms|<Et>, <hN>:<mN>:<sN>
ka|EHmzzzz|<Et>, <hN>:<mN> <zt>
ka|dh|<dn>, <hn> <at>
ka|dhm|<dn>, <hn>:<mN> <at>
ka|dHm|<dn>, <hN>:<mN>
ka|dhmz|<dn>, <hn>:<mN> <at> <zt>
ka|dHms|<dn>, <hN>:<mN>:<sN>
ka|dHmzzzz|<dn>, <hN>:<mN> <zt>
ka|MMMMdh|<dn> <Mt>, <hn> <at>
ka|MMMMdhm|<dn> <Mt>, <hn>:<mN> <at>
ka|MMMMdHm|<dn> <Mt>, <hN>:<mN>
ka|MMMMdhmz|<dn> <Mt>, <hn>:<mN> <at> <zt>
ka|MMMMdHms|<dn> <Mt>, <hN>:<mN>:<sN>
ka|MMMMdHmzzzz|<dn> <Mt>, <hN>:<mN> <zt>
ka|yMMMMEEEEdh|<Et>, <dn> <Mt>, <yn>, <hn> <at>
ka|yMMMMEEEEdhm|<Et>, <dn> <Mt>, <yn>, <hn>:<mN> <at>
ka|yMMMMEEEEdHm|<Et>, <dn> <Mt>, <yn>, <hN>:<mN>
ka|yMMMMEEEEdhmz|<Et>, <dn> <Mt>, <yn>, <hn>:<mN> <at> <zt>
ka|yMMMMEEEEdHms|<Et>, <dn> <Mt>, <yn>, <hN>:<mN>:<sN>
ka|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt>, <yn>, <hN>:<mN> <zt>
ka|EEEEdh|<dn> <Et>, <hn> <at>
ka|EEEEdhm|<dn> <Et>, <hn>:<mN> <at>
ka|EEEEdHm|<dn> <Et>, <hN>:<mN>
ka|EEEEdhmz|<dn> <Et>, <hn>:<mN> <at> <zt>
ka|EEEEdHms|<dn> <Et>, <hN>:<mN>:<sN>
ka|EEEEdHmzzzz|<dn> <Et>, <hN>:<mN> <zt>
kk|d|<dn>
kk|E|<Et>
kk|Ed|<dn>, <Et>
kk|EEEE|<Et>
kk|EEEEd|<dn>, <Et>
kk|M|<Mn>
kk|Md|<dN>.<MN>
kk|ME|<Mn> <Et>
kk|MEd|<dN>.<MN>, <Et>
kk|MEEEE|<Mn> <Et>
kk|MEEEEd|<dN>.<MN>, <Et>
kk|MM|<MN>
kk|MMd|<dN>.<MN>
kk|MME|<Et> (ай: <MN>)
kk|MMEd|<dN>.<MN>, <Et>
kk|MMEEEE|<MN> <Et>
kk|MMEEEEd|<dN>.<MN>, <Et>
kk|MMM|<Mt>
kk|MMMd|<dn> <Mt>
kk|MMME|<Mt> <Et>
kk|MMMEd|<dn> <Mt>, <Et>
kk|MMMEEEE|<Mt> <Et>
kk|MMMEEEEd|<dn> <Mt>, <Et>
kk|MMMM|<Mt>
kk|MMMMd|<dn> <Mt>
kk|MMMME|<Et> (ай: <Mt>)
kk|MMMMEd|<dn> <Mt>, <Et>
kk|MMMMEEEE|<Mt> <Et>
kk|MMMMEEEEd|<dn> <Mt>, <Et>
kk|y|<yn>
kk|yd|<yn> (күн: <dn>)
kk|yE|<yn> <Et>
kk|yEd|<yn> <dn>, <Et>
kk|yEEEE|<yn> <Et>
kk|yEEEEd|<yn> <dn>, <Et>
kk|yM|<MN>.<yn>
kk|yMd|<dN>.<MN>.<yn>
kk|yME|<MN>.<yn> <Et>
kk|yMEd|<dN>.<MN>.<yn>, <Et>
kk|yMEEEE|<MN>.<yn> <Et>
kk|yMEEEEd|<dN>.<MN>.<yn>, <Et>
kk|yMM|<MN>.<yn>
kk|yMMd|<dN>.<MN>.<yn>
kk|yMME|<MN>.<yn> <Et>
kk|yMMEd|<dN>.<MN>.<yn>, <Et>
kk|yMMEEEE|<MN>.<yn> <Et>
kk|yMMEEEEd|<dN>.<MN>.<yn>, <Et>
kk|yMMM|<yn> ж. <Mt>
kk|yMMMd|<yn> ж. <dn> <Mt>
kk|yMMME|<yn> ж. <Mt> <Et>
kk|yMMMEd|<yn> ж. <dn> <Mt>, <Et>
kk|yMMMEEEE|<yn> ж. <Mt> <Et>
kk|yMMMEEEEd|<yn> ж. <dn> <Mt>, <Et>
kk|yMMMM|<yn> ж. <Mt>
kk|yMMMMd|<yn> ж. <dn> <Mt>
kk|yMMMME|<yn> ж. <Mt> <Et>
kk|yMMMMEd|<yn> ж. <dn> <Mt>, <Et>
kk|yMMMMEEEE|<yn> ж. <Mt> <Et>
kk|yMMMMEEEEd|<yn> ж. <dn> <Mt>, <Et>
kk|Gy|<Gt> <yn> ж.
kk|GyMMMd|<Gt> <yn> ж. <dn> <Mt>
kk|GyMMMMEEEEd|<Gt> <yn> ж. <dn> <Mt>, <Et>
kk|GyMd|<dN>-<MN>-<Gt> <yn>
kk|s|<sn>
kk|sz|<sn> <zt>
kk|szzzz|<sn> <zt>
kk|m|<mn>
kk|mz|<mn> <zt>
kk|mzzzz|<mn> <zt>
kk|ms|<mN>:<sN>
kk|msz|<mN>:<sN> <zt>
kk|mszzzz|<mN>:<sN> <zt>
kk|h|<hn> <at>
kk|hz|<hn> <at>, <zt>
kk|hzzzz|<hn> <at>, <zt>
kk|hs|<hn> <at> (секунд: <sn>)
kk|hsz|<hn> <at>, <zt> (секунд: <sn>)
kk|hszzzz|<hn> <at>, <zt> (секунд: <sn>)
kk|hm|<hn>:<mN> <at>
kk|hmz|<hn>:<mN> <at> <zt>
kk|hmzzzz|<hn>:<mN> <at> <zt>
kk|hms|<hn>:<mN>:<sN> <at>
kk|hmsz|<hn>:<mN>:<sN> <at> <zt>
kk|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
kk|H|<hN>
kk|Hz|<hN>h <zt>
kk|Hzzzz|<hN>h <zt>
kk|Hs|<hN> (секунд: <sn>)
kk|Hsz|<hN>h <zt> (секунд: <sn>)
kk|Hszzzz|<hN>h <zt> (секунд: <sn>)
kk|Hm|<hN>:<mN>
kk|Hmz|<hN>:<mN> <zt>
kk|Hmzzzz|<hN>:<mN> <zt>
kk|Hms|<hN>:<mN>:<sN>
kk|Hmsz|<hn>:<mN>:<sN> <zt>
kk|Hmszzzz|<hn>:<mN>:<sN> <zt>
kk|yMMMdh|<yn> ж. <dn> <Mt>, <hn> <at>
kk|yMMMdhm|<yn> ж. <dn> <Mt>, <hn>:<mN> <at>
kk|yMMMdHm|<yn> ж. <dn> <Mt>, <hN>:<mN>
kk|yMMMdhmz|<yn> ж. <dn> <Mt>, <hn>:<mN> <at> <zt>
kk|yMMMdHms|<yn> ж. <dn> <Mt>, <hN>:<mN>:<sN>
kk|yMMMdHmzzzz|<yn> ж. <dn> <Mt>, <hN>:<mN> <zt>
kk|MMMEdh|<dn> <Mt>, <Et>, <hn> <at>
kk|MMMEdhm|<dn> <Mt>, <Et>, <hn>:<mN> <at>
kk|MMMEdHm|<dn> <Mt>, <Et>, <hN>:<mN>
kk|MMMEdhmz|<dn> <Mt>, <Et>, <hn>:<mN> <at> <zt>
kk|MMMEdHms|<dn> <Mt>, <Et>, <hN>:<mN>:<sN>
kk|MMMEdHmzzzz|<dn> <Mt>, <Et>, <hN>:<mN> <zt>
kk|yMdh|<dN>.<MN>.<yn>, <hn> <at>
kk|yMdhm|<dN>.<MN>.<yn>, <hn>:<mN> <at>
kk|yMdHm|<dN>.<MN>.<yn>, <hN>:<mN>
kk|yMdhmz|<dN>.<MN>.<yn>, <hn>:<mN> <at> <zt>
kk|yMdHms|<dN>.<MN>.<yn>, <hN>:<mN>:<sN>
kk|yMdHmzzzz|<dN>.<MN>.<yn>, <hN>:<mN> <zt>
kk|Eh|<Et>, <hn> <at>
kk|Ehm|<Et> <hn>:<mN> <at>
kk|EHm|<Et> <hN>:<mN>
kk|Ehmz|<Et>, <hn>:<mN> <at> <zt>
kk|EHms|<Et> <hN>:<mN>:<sN>
kk|EHmzzzz|<Et>, <hN>:<mN> <zt>
kk|dh|<dn>, <hn> <at>
kk|dhm|<dn>, <hn>:<mN> <at>
kk|dHm|<dn>, <hN>:<mN>
kk|dhmz|<dn>, <hn>:<mN> <at> <zt>
kk|dHms|<dn>, <hN>:<mN>:<sN>
kk|dHmzzzz|<dn>, <hN>:<mN> <zt>
kk|MMMMdh|<dn> <Mt>, <hn> <at>
kk|MMMMdhm|<dn> <Mt>, <hn>:<mN> <at>
kk|MMMMdHm|<dn> <Mt>, <hN>:<mN>
kk|MMMMdhmz|<dn> <Mt>, <hn>:<mN> <at> <zt>
kk|MMMMdHms|<dn> <Mt>, <hN>:<mN>:<sN>
kk|MMMMdHmzzzz|<dn> <Mt>, <hN>:<mN> <zt>
kk|yMMMMEEEEdh|<yn> ж. <dn> <Mt>, <Et>, <hn> <at>
kk|yMMMMEEEEdhm|<yn> ж. <dn> <Mt>, <Et>, <hn>:<mN> <at>
kk|yMMMMEEEEdHm|<yn> ж. <dn> <Mt>, <Et>, <hN>:<mN>
kk|yMMMMEEEEdhmz|<yn> ж. <dn> <Mt>, <Et>, <hn>:<mN> <at> <zt>
kk|yMMMMEEEEdHms|<yn> ж. <dn> <Mt>, <Et>, <hN>:<mN>:<sN>
kk|yMMMMEEEEdHmzzzz|<yn> ж. <dn> <Mt>, <Et>, <hN>:<mN> <zt>
kk|EEEEdh|<dn>, <Et>, <hn> <at>
kk|EEEEdhm|<dn>, <Et>, <hn>:<mN> <at>
kk|EEEEdHm|<dn>, <Et>, <hN>:<mN>
kk|EEEEdhmz|<dn>, <Et>, <hn>:<mN> <at> <zt>
kk|EEEEdHms|<dn>, <Et>, <hN>:<mN>:<sN>
kk|EEEEdHmzzzz|<dn>, <Et>, <hN>:<mN> <zt>
ko|d|<dn>일
ko|E|<Et>
ko|Ed|<dn>일 (<Et>)
ko|EEEE|<Et>
ko|EEEEd|<dn>일 <Et>
ko|M|<Mn>월
ko|Md|<Mn>. <dn>.
ko|ME|<Mn>월 <Et>
ko|MEd|<Mn>. <dn>. (<Et>)
ko|MEEEE|<Mn>월 <Et>
ko|MEEEEd|<Mn>. <dn>. <Et>
ko|MM|<MN>월
ko|MMd|<MN>. <dn>.
ko|MME|<Et> (월: <MN>월)
ko|MMEd|<MN>. <dn>. (<Et>)
ko|MMEEEE|<MN>월 <Et>
ko|MMEEEEd|<MN>. <dn>. <Et>
ko|MMM|<Mt>
ko|MMMd|<Mt> <dn>일
ko|MMME|<Mt> <Et>
ko|MMMEd|<Mt> <dn>일 (<Et>)
ko|MMMEEEE|<Mt> <Et>
ko|MMMEEEEd|<Mt> <dn>일 <Et>
ko|MMMM|<Mt>
ko|MMMMd|<Mt> <dn>일
ko|MMMME|<Et> (월: <Mt>)
ko|MMMMEd|<Mt> <dn>일 (<Et>)
ko|MMMMEEEE|<Mt> <Et>
ko|MMMMEEEEd|<Mt> <dn>일 <Et>
ko|y|<yn>년
ko|yd|<yn>년 (일: <dn>일)
ko|yE|<yn>년 <Et>
ko|yEd|<yn>년 <dn>일 (<Et>)
ko|yEEEE|<yn>년 <Et>
ko|yEEEEd|<yn>년 <dn>일 <Et>
ko|yM|<yn>. <Mn>.
ko|yMd|<yn>. <Mn>. <dn>.
ko|yME|<yn>. <Mn>. <Et>
ko|yMEd|<yn>. <Mn>. <dn>. (<Et>)
ko|yMEEEE|<yn>. <Mn>. <Et>
ko|yMEEEEd|<yn>. <Mn>. <dn>. <Et>
ko|yMM|<yn>. <Mn>.
ko|yMMd|<yn>. <MN>. <dn>.
ko|yMME|<yn>. <Mn>. <Et>
ko|yMMEd|<yn>. <MN>. <dn>. (<Et>)
ko|yMMEEEE|<yn>. <Mn>. <Et>
ko|yMMEEEEd|<yn>. <MN>. <dn>. <Et>
ko|yMMM|<yn>년 <Mt>
ko|yMMMd|<yn>년 <Mt> <dn>일
ko|yMMME|<yn>년 <Mt> <Et>
ko|yMMMEd|<yn>년 <Mt> <dn>일 (<Et>)
ko|yMMMEEEE|<yn>년 <Mt> <Et>
ko|yMMMEEEEd|<yn>년 <Mt> <dn>일 <Et>
ko|yMMMM|<yn>년 <Mt>
ko|yMMMMd|<yn>년 <Mt> <dn>일
ko|yMMMME|<yn>년 <Mt> <Et>
ko|yMMMMEd|<yn>년 <Mt> <dn>일 <Et>
ko|yMMMMEEEE|<yn>년 <Mt> <Et>
ko|yMMMMEEEEd|<yn>년 <Mt> <dn>일 <Et>
ko|Gy|<Gt> <yn>년
ko|GyMMMd|<Gt> <yn>년 <Mt> <dn>일
ko|GyMMMMEEEEd|<Gt> <yn>년 <Mt> <dn>일 <Et>
ko|GyMd|<Gt> <yn>/<Mn>/<dn>
ko|s|<sn>
ko|sz|<sn> <zt>
ko|szzzz|<sn> <zt>
ko|m|<mn>
ko|mz|<mn> <zt>
ko|mzzzz|<mn> <zt>
ko|ms|<mN>:<sN>
ko|msz|<mN>:<sN> <zt>
ko|mszzzz|<mN>:<sN> <zt>
ko|h|<at> <hn>시
ko|hz|<at> <hn>시 <zt>
ko|hzzzz|<at> <hn>시 <zt>
ko|hs|<at> <hn>시 (초: <sn>)
ko|hsz|<at> <hn>시 <zt> (초: <sn>)
ko|hszzzz|<at> <hn>시 <zt> (초: <sn>)
ko|hm|<at> <hn>:<mN>
ko|hmz|<at> <hn>:<mN> <zt>
ko|hmzzzz|<at> <hn>:<mN> <zt>
ko|hms|<at> <hn>:<mN>:<sN>
ko|hmsz|<at> <hn>시 <mn>분 <sn>초 <zt>
ko|hmszzzz|<at> <hn>시 <mn>분 <sn>초 <zt>
ko|H|<hn>시
ko|Hz|<hn>시 <zt>
ko|Hzzzz|<hn>시 <zt>
ko|Hs|<hn>시 (초: <sn>)
ko|Hsz|<hn>시 <zt> (초: <sn>)
ko|Hszzzz|<hn>시 <zt> (초: <sn>)
ko|Hm|<hN>:<mN>
ko|Hmz|<hN>:<mN> <zt>
ko|Hmzzzz|<hN>:<mN> <zt>
ko|Hms|<hn>시 <mn>분 <sn>초
ko|Hmsz|<hn>시 <mn>분 <sn>초 <zt>
ko|Hmszzzz|<hn>시 <mn>분 <sn>초 <zt>
ko|yMMMdh|<yn>년 <Mt> <dn>일 <at> <hn>시
ko|yMMMdhm|<yn>년 <Mt> <dn>일 <at> <hn>:<mN>
ko|yMMMdHm|<yn>년 <Mt> <dn>일 <hN>:<mN>
ko|yMMMdhmz|<yn>년 <Mt> <dn>일 <at> <hn>:<mN> <zt>
ko|yMMMdHms|<yn>년 <Mt> <dn>일 <hn>시 <mn>분 <sn>초
ko|yMMMdHmzzzz|<yn>년 <Mt> <dn>일 <hN>:<mN> <zt>
ko|MMMEdh|<Mt> <dn>일 (<Et>) <at> <hn>시
ko|MMMEdhm|<Mt> <dn>일 (<Et>) <at> <hn>:<mN>
ko|MMMEdHm|<Mt> <dn>일 (<Et>) <hN>:<mN>
ko|MMMEdhmz|<Mt> <dn>일 (<Et>) <at> <hn>:<mN> <zt>
ko|MMMEdHms|<Mt> <dn>일 (<Et>) <hn>시 <mn>분 <sn>초
ko|MMMEdHmzzzz|<Mt> <dn>일 (<Et>) <hN>:<mN> <zt>
ko|yMdh|<yn>. <Mn>. <dn>. <at> <hn>시
ko|yMdhm|<yn>. <Mn>. <dn>. <at> <hn>:<mN>
ko|yMdHm|<yn>. <Mn>. <dn>. <hN>:<mN>
ko|yMdhmz|<yn>. <Mn>. <dn>. <at> <hn>:<mN> <zt>
ko|yMdHms|<yn>. <Mn>. <dn>. <hn>시 <mn>분 <sn>초
ko|yMdHmzzzz|<yn>. <Mn>. <dn>. <hN>:<mN> <zt>
ko|Eh|(<Et>) <at> <hn>
ko|Ehm|(<Et>) <at> <hn>:<mN>
ko|EHm|(<Et>) <hN>:<mN>
ko|Ehmz|<Et> <at> <hn>:<mN> <zt>
ko|EHms|(<Et>) <hN>:<mN>:<sN>
ko|EHmzzzz|<Et> <hN>:<mN> <zt>
ko|dh|<dn>일 <at> <hn>시
ko|dhm|<dn>일 <at> <hn>:<mN>
ko|dHm|<dn>일 <hN>:<mN>
ko|dhmz|<dn>일 <at> <hn>:<mN> <zt>
ko|dHms|<dn>일 <hn>시 <mn>분 <sn>초
ko|dHmzzzz|<dn>일 <hN>:<mN> <zt>
ko|MMMMdh|<Mt> <dn>일 <at> <hn>시
ko|MMMMdhm|<Mt> <dn>일 <at> <hn>:<mN>
ko|MMMMdHm|<Mt> <dn>일 <hN>:<mN>
ko|MMMMdhmz|<Mt> <dn>일 <at> <hn>:<mN> <zt>
ko|MMMMdHms|<Mt> <dn>일 <hn>시 <mn>분 <sn>초
ko|MMMMdHmzzzz|<Mt> <dn>일 <hN>:<mN> <zt>
ko|yMMMMEEEEdh|<yn>년 <Mt> <dn>일 <Et> <at> <hn>시
ko|yMMMMEEEEdhm|<yn>년 <Mt> <dn>일 <Et> <at> <hn>:<mN>
ko|yMMMMEEEEdHm|<yn>년 <Mt> <dn>일 <Et> <hN>:<mN>
ko|yMMMMEEEEdhmz|<yn>년 <Mt> <dn>일 <Et> <at> <hn>:<mN> <zt>
ko|yMMMMEEEEdHms|<yn>년 <Mt> <dn>일 <Et> <hn>시 <mn>분 <sn>초
ko|yMMMMEEEEdHmzzzz|<yn>년 <Mt> <dn>일 <Et> <hN>:<mN> <zt>
ko|EEEEdh|<dn>일 <Et> <at> <hn>시
ko|EEEEdhm|<dn>일 <Et> <at> <hn>:<mN>
ko|EEEEdHm|<dn>일 <Et> <hN>:<mN>
ko|EEEEdhmz|<dn>일 <Et> <at> <hn>:<mN> <zt>
ko|EEEEdHms|<dn>일 <Et> <hn>시 <mn>분 <sn>초
ko|EEEEdHmzzzz|<dn>일 <Et> <hN>:<mN> <zt>
lt|d|<dN>
lt|E|<Et>
lt|Ed|<dn>, <Et>
lt|EEEE|<Et>
lt|EEEEd|<dn>, <Et>
lt|M|<MN>
lt|Md|<MN>-<dn>
lt|ME|<MN> <Et>
lt|MEd|<MN>-<dN>, <Et>
lt|MEEEE|<MN> <Et>
lt|MEEEEd|<MN>-<dN>, <Et>
lt|MM|<MN>
lt|MMd|<MN>-<dn>
lt|MME|<Et> (mėnuo: <MN>)
lt|MMEd|<MN>-<dN>, <Et>
lt|MMEEEE|<MN> <Et>
lt|MMEEEEd|<MN>-<dN>, <Et>
lt|MMM|<MN>
lt|MMMd|<MN>-<dN>
lt|MMME|<MN> <Et>
lt|MMMEd|<MN>-<dN>, <Et>
lt|MMMEEEE|<MN> <Et>
lt|MMMEEEEd|<MN>-<dN>, <Et>
lt|MMMM|<Mt>
lt|MMMMd|<Mt> <dn> d.
lt|MMMME|<Mt> <Et>
lt|MMMMEd|<Mt> <dn> d., <Et>
lt|MMMMEEEE|<Mt> <Et>
lt|MMMMEEEEd|<Mt> <dn> d., <Et>
lt|y|<yn>
lt|yd|<yn> (diena: <dN>)
lt|yE|<yn> <Et>
lt|yEd|<yn> <dn>, <Et>
lt|yEEEE|<yn> <Et>
lt|yEEEEd|<yn> <dn>, <Et>
lt|yM|<yn>-<MN>
lt|yMd|<yn>-<MN>-<dN>
lt|yME|<yn>-<MN> <Et>
lt|yMEd|<yn>-<MN>-<dN>, <Et>
lt|yMEEEE|<yn>-<MN> <Et>
lt|yMEEEEd|<yn>-<MN>-<dN>, <Et>
lt|yMM|<yn>-<MN>
lt|yMMd|<yn>-<MN>-<dn>
lt|yMME|<yn>-<MN> <Et>
lt|yMMEd|<yn>-<MN>-<dN>, <Et>
lt|yMMEEEE|<yn>-<MN> <Et>
lt|yMMEEEEd|<yn>-<MN>-<dN>, <Et>
lt|yMMM|<yn>-<MN>
lt|yMMMd|<yn>-<MN>-<dN>
lt|yMMME|<yn>-<MN> <Et>
lt|yMMMEd|<yn>-<MN>-<dN>, <Et>
lt|yMMMEEEE|<yn>-<MN> <Et>
lt|yMMMEEEEd|<yn> m. <Mt> <dn> d., <Et>
lt|yMMMM|<yn> m. <Mt>
lt|yMMMMd|<yn> m. <Mt> <dn> d.
lt|yMMMME|<yn> m. <Mt> <Et>
lt|yMMMMEd|<yn> m. <Mt> <dn> d., <Et>
lt|yMMMMEEEE|<yn> m. <Mt> <Et>
lt|yMMMMEEEEd|<yn> m. <Mt> <dn> d., <Et>
lt|Gy|<yn> m. <Gt>
lt|GyMMMd|<yn>-<MN>-<dN> <Gt>
lt|GyMMMMEEEEd|<yn> m. <Gt> <Mt> <dn> d., <Et>
lt|GyMd|<yn>-<MN>-<dN> <Gt>
lt|s|<sn>
lt|sz|<sn> <zt>
lt|szzzz|<sn> <zt>
lt|m|<mn>
lt|mz|<mn> <zt>
lt|mzzzz|<mn> <zt>
lt|ms|<mN>:<sN>
lt|msz|<mN>:<sN> <zt>
lt|mszzzz|<mN>:<sN> <zt>
lt|h|<hN> <at>
lt|hz|<hn> <at> <zt>
lt|hzzzz|<hn> <at> <zt>
lt|hs|<hN> <at> (sekundė: <sn>)
lt|hsz|<hn> <at> <zt> (sekundė: <sn>)
lt|hszzzz|<hn> <at> <zt> (sekundė: <sn>)
lt|hm|<hN>:<mN> <at>
lt|hmz|<hN>:<mN> <at>; <zt>
lt|hmzzzz|<hN>:<mN> <at>; <zt>
lt|hms|<hN>:<mN>:<sN> <at>
lt|hmsz|<hN>:<mN>:<sN> <at>; <zt>
lt|hmszzzz|<hN>:<mN>:<sN> <at>; <zt>
lt|H|<hN>
lt|Hz|<hN> h <zt>
lt|Hzzzz|<hN> h <zt>
lt|Hs|<hN> (sekundė: <sn>)
lt|Hsz|<hN> h <zt> (sekundė: <sn>)
lt|Hszzzz|<hN> h <zt> (sekundė: <sn>)
lt|Hm|<hN>:<mN>
lt|Hmz|<hN>:<mN>; <zt>
lt|Hmzzzz|<hN>:<mN>; <zt>
lt|Hms|<hN>:<mN>:<sN>
lt|Hmsz|<hn>:<mN>:<sN> <zt>
lt|Hmszzzz|<hn>:<mN>:<sN> <zt>
lt|yMMMdh|<yn>-<MN>-<dN> <hN> <at>
lt|yMMMdhm|<yn>-<MN>-<dN> <hN>:<mN> <at>
lt|yMMMdHm|<yn>-<MN>-<dN> <hN>:<mN>
lt|yMMMdhmz|<yn>-<MN>-<dN> <hN>:<mN> <at>; <zt>
lt|yMMMdHms|<yn>-<MN>-<dN> <hN>:<mN>:<sN>
lt|yMMMdHmzzzz|<yn>-<MN>-<dN> <hN>:<mN>; <zt>
lt|MMMEdh|<MN>-<dN>, <Et> <hN> <at>
lt|MMMEdhm|<MN>-<dN>, <Et> <hN>:<mN> <at>
lt|MMMEdHm|<MN>-<dN>, <Et> <hN>:<mN>
lt|MMMEdhmz|<MN>-<dN>, <Et> <hN>:<mN> <at>; <zt>
lt|MMMEdHms|<MN>-<dN>, <Et> <hN>:<mN>:<sN>
lt|MMMEdHmzzzz|<MN>-<dN>, <Et> <hN>:<mN>; <zt>
lt|yMdh|<yn>-<MN>-<dN> <hN> <at>
lt|yMdhm|<yn>-<MN>-<dN> <hN>:<mN> <at>
lt|yMdHm|<yn>-<MN>-<dN> <hN>:<mN>
lt|yMdhmz|<yn>-<MN>-<dN> <hN>:<mN> <at>; <zt>
lt|yMdHms|<yn>-<MN>-<dN> <hN>:<mN>:<sN>
lt|yMdHmzzzz|<yn>-<MN>-<dN> <hN>:<mN>; <zt>
lt|Eh|<hn> <at>, <Et>
lt|Ehm|<hN>:<mN> <at>, <Et>
lt|EHm|<hN>:<mN>, <Et>
lt|Ehmz|<Et> <hN>:<mN> <at>; <zt>
lt|EHms|<hN>:<mN>:<sN>, <Et>
lt|EHmzzzz|<Et> <hN>:<mN>; <zt>
lt|dh|<dN> <hN> <at>
lt|dhm|<dN> <hN>:<mN> <at>
lt|dHm|<dN> <hN>:<mN>
lt|dhmz|<dN> <hN>:<mN> <at>; <zt>
lt|dHms|<dN> <hN>:<mN>:<sN>
lt|dHmzzzz|<dN> <hN>:<mN>; <zt>
lt|MMMMdh|<Mt> <dn> d. <hN> <at>
lt|MMMMdhm|<Mt> <dn> d. <hN>:<mN> <at>
lt|MMMMdHm|<Mt> <dn> d. <hN>:<mN>
lt|MMMMdhmz|<Mt> <dn> d. <hN>:<mN> <at>; <zt>
lt|MMMMdHms|<Mt> <dn> d. <hN>:<mN>:<sN>
lt|MMMMdHmzzzz|<Mt> <dn> d. <hN>:<mN>; <zt>
lt|yMMMMEEEEdh|<yn> m. <Mt> <dn> d., <Et> <hN> <at>
lt|yMMMMEEEEdhm|<yn> m. <Mt> <dn> d., <Et> <hN>:<mN> <at>
lt|yMMMMEEEEdHm|<yn> m. <Mt> <dn> d., <Et> <hN>:<mN>
lt|yMMMMEEEEdhmz|<yn> m. <Mt> <dn> d., <Et> <hN>:<mN> <at>; <zt>
lt|yMMMMEEEEdHms|<yn> m. <Mt> <dn> d., <Et> <hN>:<mN>:<sN>
lt|yMMMMEEEEdHmzzzz|<yn> m. <Mt> <dn> d., <Et> <hN>:<mN>; <zt>
lt|EEEEdh|<dn>, <Et> <hN> <at>
lt|EEEEdhm|<dn>, <Et> <hN>:<mN> <at>
lt|EEEEdHm|<dn>, <Et> <hN>:<mN>
lt|EEEEdhmz|<dn>, <Et> <hN>:<mN> <at>; <zt>
lt|EEEEdHms|<dn>, <Et> <hN>:<mN>:<sN>
lt|EEEEdHmzzzz|<dn>, <Et> <hN>:<mN>; <zt>
lv|d|<dn>
lv|E|<Et>
lv|Ed|<Et>, <dn>.
lv|EEEE|<Et>
lv|EEEEd|<Et>, <dn>.
lv|M|<Mn>
lv|Md|<dN>.<MN>.
lv|ME|<Mn> <Et>
lv|MEd|<Et>, <dN>.<MN>.
lv|MEEEE|<Mn> <Et>
lv|MEEEEd|<Et>, <dN>.<MN>.
lv|MM|<MN>
lv|MMd|<dN>.<MN>.
lv|MME|<Et> (mēnesis: <MN>)
lv|MMEd|<Et>, <dN>.<MN>.
lv|MMEEEE|<MN> <Et>
lv|MMEEEEd|<Et>, <dN>.<MN>.
lv|MMM|<Mt>
lv|MMMd|<dn>. <Mt>
lv|MMME|<Mt> <Et>
lv|MMMEd|<Et>, <dn>. <Mt>
lv|MMMEEEE|<Mt> <Et>
lv|MMMEEEEd|<Et>, <dn>. <Mt>
lv|MMMM|<Mt>
lv|MMMMd|<dn>. <Mt>
lv|MMMME|<Et> (mēnesis: <Mt>)
lv|MMMMEd|<Et>, <dn>. <Mt>
lv|MMMMEEEE|<Mt> <Et>
lv|MMMMEEEEd|<Et>, <dn>. <Mt>
lv|y|<yn>. g.
lv|yd|<yn>. g. (diena: <dn>)
lv|yE|<yn>. g. <Et>
lv|yEd|<yn>. g. <Et>, <dn>.
lv|yEEEE|<yn>. g. <Et>
lv|yEEEEd|<yn>. g. <Et>, <dn>.
lv|yM|<MN>.<yn>.
lv|yMd|<dn>.<MN>.<yn>.
lv|yME|<MN>.<yn>. <Et>
lv|yMEd|<Et>, <dn>.<MN>.<yn>.
lv|yMEEEE|<MN>.<yn>. <Et>
lv|yMEEEEd|<Et>, <dn>.<MN>.<yn>.
lv|yMM|<MN>.<yn>.
lv|yMMd|<dn>.<MN>.<yn>.
lv|yMME|<MN>.<yn>. <Et>
lv|yMMEd|<Et>, <dn>.<MN>.<yn>.
lv|yMMEEEE|<MN>.<yn>. <Et>
lv|yMMEEEEd|<Et>, <dn>.<MN>.<yn>.
lv|yMMM|<yn>. g. <Mt>
lv|yMMMd|<yn>. g. <dn>. <Mt>
lv|yMMME|<yn>. g. <Mt> <Et>
lv|yMMMEd|<Et>, <yn>. g. <dn>. <Mt>
lv|yMMMEEEE|<yn>. g. <Mt> <Et>
lv|yMMMEEEEd|<Et>, <yn>. gada <dn>. <Mt>
lv|yMMMM|<yn>. g. <Mt>
lv|yMMMMd|<yn>. gada <dn>. <Mt>
lv|yMMMME|<yn>. g. <Mt> <Et>
lv|yMMMMEd|<Et>, <yn>. gada <dn>. <Mt>
lv|yMMMMEEEE|<yn>. g. <Mt> <Et>
lv|yMMMMEEEEd|<Et>, <yn>. gada <dn>. <Mt>
lv|Gy|<Gt> <yn>. g.
lv|GyMMMd|<Gt> <yn>. g. <dn>. <Mt>
lv|GyMMMMEEEEd|<Et>, <Gt> <yn>. g. <dn>. <Mt>
lv|GyMd|<Gt> <dN>-<MN>-<yn>
lv|s|<sn>
lv|sz|<sn> <zt>
lv|szzzz|<sn> <zt>
lv|m|<mn>
lv|mz|<mn> <zt>
lv|mzzzz|<mn> <zt>
lv|ms|<mN>:<sN>
lv|msz|<mN>:<sN> <zt>
lv|mszzzz|<mN>:<sN> <zt>
lv|h|<hn> <at>
lv|hz|<hn> <at> <zt>
lv|hzzzz|<hn> <at> <zt>
lv|hs|<hn> <at> (sekundes: <sn>)
lv|hsz|<hn> <at> <zt> (sekundes: <sn>)
lv|hszzzz|<hn> <at> <zt> (sekundes: <sn>)
lv|hm|<hn>:<mN> <at>
lv|hmz|<hn>:<mN> <at> <zt>
lv|hmzzzz|<hn>:<mN> <at> <zt>
lv|hms|<hn>:<mN>:<sN> <at>
lv|hmsz|<hn>:<mN>:<sN> <at> <zt>
lv|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
lv|H|<hN>
lv|Hz|<hN>h <zt>
lv|Hzzzz|<hN>h <zt>
lv|Hs|<hN> (sekundes: <sn>)
lv|Hsz|<hN>h <zt> (sekundes: <sn>)
lv|Hszzzz|<hN>h <zt> (sekundes: <sn>)
lv|Hm|<hN>:<mN>
lv|Hmz|<hN>:<mN> <zt>
lv|Hmzzzz|<hN>:<mN> <zt>
lv|Hms|<hN>:<mN>:<sN>
lv|Hmsz|<hn>:<mN>:<sN> <zt>
lv|Hmszzzz|<hn>:<mN>:<sN> <zt>
lv|yMMMdh|<yn>. g. <dn>. <Mt> <hn> <at>
lv|yMMMdhm|<yn>. g. <dn>. <Mt> <hn>:<mN> <at>
lv|yMMMdHm|<yn>. g. <dn>. <Mt> <hN>:<mN>
lv|yMMMdhmz|<yn>. g. <dn>. <Mt> <hn>:<mN> <at> <zt>
lv|yMMMdHms|<yn>. g. <dn>. <Mt> <hN>:<mN>:<sN>
lv|yMMMdHmzzzz|<yn>. g. <dn>. <Mt> <hN>:<mN> <zt>
lv|MMMEdh|<Et>, <dn>. <Mt> <hn> <at>
lv|MMMEdhm|<Et>, <dn>. <Mt> <hn>:<mN> <at>
lv|MMMEdHm|<Et>, <dn>. <Mt> <hN>:<mN>
lv|MMMEdhmz|<Et>, <dn>. <Mt> <hn>:<mN> <at> <zt>
lv|MMMEdHms|<Et>, <dn>. <Mt> <hN>:<mN>:<sN>
lv|MMMEdHmzzzz|<Et>, <dn>. <Mt> <hN>:<mN> <zt>
lv|yMdh|<dn>.<MN>.<yn>. <hn> <at>
lv|yMdhm|<dn>.<MN>.<yn>. <hn>:<mN> <at>
lv|yMdHm|<dn>.<MN>.<yn>. <hN>:<mN>
lv|yMdhmz|<dn>.<MN>.<yn>. <hn>:<mN> <at> <zt>
lv|yMdHms|<dn>.<MN>.<yn>. <hN>:<mN>:<sN>
lv|yMdHmzzzz|<dn>.<MN>.<yn>. <hN>:<mN> <zt>
lv|Eh|<Et> <hn> <at>
lv|Ehm|<Et>, <hn>:<mN> <at>
lv|EHm|<Et>, <hN>:<mN>
lv|Ehmz|<Et> <hn>:<mN> <at> <zt>
lv|EHms|<Et>, <hN>:<mN>:<sN>
lv|EHmzzzz|<Et> <hN>:<mN> <zt>
lv|dh|<dn> <hn> <at>
lv|dhm|<dn> <hn>:<mN> <at>
lv|dHm|<dn> <hN>:<mN>
lv|dhmz|<dn> <hn>:<mN> <at> <zt>
lv|dHms|<dn> <hN>:<mN>:<sN>
lv|dHmzzzz|<dn> <hN>:<mN> <zt>
lv|MMMMdh|<dn>. <Mt> <hn> <at>
lv|MMMMdhm|<dn>. <Mt> <hn>:<mN> <at>
lv|MMMMdHm|<dn>. <Mt> <hN>:<mN>
lv|MMMMdhmz|<dn>. <Mt> <hn>:<mN> <at> <zt>
lv|MMMMdHms|<dn>. <Mt> <hN>:<mN>:<sN>
lv|MMMMdHmzzzz|<dn>. <Mt> <hN>:<mN> <zt>
lv|yMMMMEEEEdh|<Et>, <yn>. gada <dn>. <Mt> <hn> <at>
lv|yMMMMEEEEdhm|<Et>, <yn>. gada <dn>. <Mt> <hn>:<mN> <at>
lv|yMMMMEEEEdHm|<Et>, <yn>. gada <dn>. <Mt> <hN>:<mN>
lv|yMMMMEEEEdhmz|<Et>, <yn>. gada <dn>. <Mt> <hn>:<mN> <at> <zt>
lv|yMMMMEEEEdHms|<Et>, <yn>. gada <dn>. <Mt> <hN>:<mN>:<sN>
lv|yMMMMEEEEdHmzzzz|<Et>, <yn>. gada <dn>. <Mt> <hN>:<mN> <zt>
lv|EEEEdh|<Et>, <dn>. <hn> <at>
lv|EEEEdhm|<Et>, <dn>. <hn>:<mN> <at>
lv|EEEEdHm|<Et>, <dn>. <hN>:<mN>
lv|EEEEdhmz|<Et>, <dn>. <hn>:<mN> <at> <zt>
lv|EEEEdHms|<Et>, <dn>. <hN>:<mN>:<sN>
lv|EEEEdHmzzzz|<Et>, <dn>. <hN>:<mN> <zt>
mk|d|<dn>
mk|E|<Et>
mk|Ed|<Et>, <dn>
mk|EEEE|<Et>
mk|EEEEd|<Et>, <dn>
mk|M|<Mn>
mk|Md|<dn>.<Mn>
mk|ME|<Mn> <Et>
mk|MEd|<Et>, <dn>.<Mn>
mk|MEEEE|<Mn> <Et>
mk|MEEEEd|<Et>, <dn>.<Mn>
mk|MM|<MN>
mk|MMd|<dn>.<MN>
mk|MME|<Et> (месец: <MN>)
mk|MMEd|<Et>, <dn>.<MN>
mk|MMEEEE|<MN> <Et>
mk|MMEEEEd|<Et>, <dn>.<MN>
mk|MMM|<Mt>
mk|MMMd|<dn> <Mt>
mk|MMME|<Mt> <Et>
mk|MMMEd|<Et>, <dn> <Mt>
mk|MMMEEEE|<Mt> <Et>
mk|MMMEEEEd|<Et>, <dn> <Mt>
mk|MMMM|<Mt>
mk|MMMMd|<dn> <Mt>
mk|MMMME|<Et> (месец: <Mt>)
mk|MMMMEd|<Et>, <dn> <Mt>
mk|MMMMEEEE|<Mt> <Et>
mk|MMMMEEEEd|<Et>, <dn> <Mt>
mk|y|<yn> г.
mk|yd|<yn> г. (ден: <dn>)
mk|yE|<yn> г. <Et>
mk|yEd|<yn> г. <Et>, <dn>
mk|yEEEE|<yn> г. <Et>
mk|yEEEEd|<yn> г. <Et>, <dn>
mk|yM|<Mn>.<yn> г.
mk|yMd|<dn>.<Mn>.<yn> г.
mk|yME|<Mn>.<yn> г. <Et>
mk|yMEd|<Et>, <dn>.<Mn>.<yn> г.
mk|yMEEEE|<Mn>.<yn> г. <Et>
mk|yMEEEEd|<Et>, <dn>.<Mn>.<yn> г.
mk|yMM|<MN>.<yn> г.
mk|yMMd|<dn>.<MN>.<yn> г.
mk|yMME|<MN>.<yn> г. <Et>
mk|yMMEd|<Et>, <dn>.<MN>.<yn> г.
mk|yMMEEEE|<MN>.<yn> г. <Et>
mk|yMMEEEEd|<Et>, <dn>.<MN>.<yn> г.
mk|yMMM|<Mt> <yn> г.
mk|yMMMd|<dn> <Mt> <yn> г.
mk|yMMME|<Mt> <yn> г. <Et>
mk|yMMMEd|<Et>, <dn> <Mt> <yn> г.
mk|yMMMEEEE|<Mt> <yn> г. <Et>
mk|yMMMEEEEd|<Et>, <dn> <Mt> <yn> г.
mk|yMMMM|<Mt> <yn> г.
mk|yMMMMd|<dn> <Mt> <yn> г.
mk|yMMMME|<Mt> <yn> г. <Et>
mk|yMMMMEd|<Et>, <dn> <Mt> <yn> г.
mk|yMMMMEEEE|<Mt> <yn> г. <Et>
mk|yMMMMEEEEd|<Et>, <dn> <Mt> <yn> г.
mk|Gy|<yn> г. <Gt>
mk|GyMMMd|<dn> <Mt> <yn> г. <Gt>
mk|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> г. <Gt>
mk|GyMd|<dn>.<Mn>.<yn> г. <Gt>
mk|s|<sn>
mk|sz|<sn> <zt>
mk|szzzz|<sn> <zt>
mk|m|<mn>
mk|mz|<mn> <zt>
mk|mzzzz|<mn> <zt>
mk|ms|<mN>:<sN>
mk|msz|<mN>:<sN> <zt>
mk|mszzzz|<mN>:<sN> <zt>
mk|h|<hn> <at>
mk|hz|<hn> <at> <zt>
mk|hzzzz|<hn> <at> <zt>
mk|hs|<hn> <at> (секунда: <sn>)
mk|hsz|<hn> <at> <zt> (секунда: <sn>)
mk|hszzzz|<hn> <at> <zt> (секунда: <sn>)
mk|hm|<hn>:<mN> <at>
mk|hmz|<hn>:<mN> <at> <zt>
mk|hmzzzz|<hn>:<mN> <at> <zt>
mk|hms|<hn>:<mN>:<sN> <at>
mk|hmsz|<hn>:<mN>:<sN> <at> <zt>
mk|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
mk|H|<hn>
mk|Hz|<hN> ч. <zt>
mk|Hzzzz|<hN> ч. <zt>
mk|Hs|<hn> (секунда: <sn>)
mk|Hsz|<hN> ч. <zt> (секунда: <sn>)
mk|Hszzzz|<hN> ч. <zt> (секунда: <sn>)
mk|Hm|<hN>:<mN>
mk|Hmz|<hN>:<mN> <zt>
mk|Hmzzzz|<hN>:<mN> <zt>
mk|Hms|<hN>:<mN>:<sN>
mk|Hmsz|<hn>:<mN>:<sN> <zt>
mk|Hmszzzz|<hn>:<mN>:<sN> <zt>
mk|yMMMdh|<dn> <Mt> <yn> г., во <hn> <at>
mk|yMMMdhm|<dn> <Mt> <yn> г., во <hn>:<mN> <at>
mk|yMMMdHm|<dn> <Mt> <yn> г., во <hN>:<mN>
mk|yMMMdhmz|<dn> <Mt> <yn> г., во <hn>:<mN> <at> <zt>
mk|yMMMdHms|<dn> <Mt> <yn> г., во <hN>:<mN>:<sN>
mk|yMMMdHmzzzz|<dn> <Mt> <yn> г., во <hN>:<mN> <zt>
mk|MMMEdh|<Et>, <dn> <Mt>, во <hn> <at>
mk|MMMEdhm|<Et>, <dn> <Mt>, во <hn>:<mN> <at>
mk|MMMEdHm|<Et>, <dn> <Mt>, во <hN>:<mN>
mk|MMMEdhmz|<Et>, <dn> <Mt>, во <hn>:<mN> <at> <zt>
mk|MMMEdHms|<Et>, <dn> <Mt>, во <hN>:<mN>:<sN>
mk|MMMEdHmzzzz|<Et>, <dn> <Mt>, во <hN>:<mN> <zt>
mk|yMdh|<dn>.<Mn>.<yn> г., <hn> <at>
mk|yMdhm|<dn>.<Mn>.<yn> г., <hn>:<mN> <at>
mk|yMdHm|<dn>.<Mn>.<yn> г., <hN>:<mN>
mk|yMdhmz|<dn>.<Mn>.<yn> г., <hn>:<mN> <at> <zt>
mk|yMdHms|<dn>.<Mn>.<yn> г., <hN>:<mN>:<sN>
mk|yMdHmzzzz|<dn>.<Mn>.<yn> г., <hN>:<mN> <zt>
mk|Eh|<Et> <hn> <at>
mk|Ehm|<Et> <hn>:<mN> <at>
mk|EHm|<Et> <hN>:<mN>
mk|Ehmz|<Et>, <hn>:<mN> <at> <zt>
mk|EHms|<Et> <hN>:<mN>:<sN>
mk|EHmzzzz|<Et>, <hN>:<mN> <zt>
mk|dh|<dn>, <hn> <at>
mk|dhm|<dn>, <hn>:<mN> <at>
mk|dHm|<dn>, <hN>:<mN>
mk|dhmz|<dn>, <hn>:<mN> <at> <zt>
mk|dHms|<dn>, <hN>:<mN>:<sN>
mk|dHmzzzz|<dn>, <hN>:<mN> <zt>
mk|MMMMdh|<dn> <Mt>, во <hn> <at>
mk|MMMMdhm|<dn> <Mt>, во <hn>:<mN> <at>
mk|MMMMdHm|<dn> <Mt>, во <hN>:<mN>
mk|MMMMdhmz|<dn> <Mt>, во <hn>:<mN> <at> <zt>
mk|MMMMdHms|<dn> <Mt>, во <hN>:<mN>:<sN>
mk|MMMMdHmzzzz|<dn> <Mt>, во <hN>:<mN> <zt>
mk|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> г., во <hn> <at>
mk|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> г., во <hn>:<mN> <at>
mk|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> г., во <hN>:<mN>
mk|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> г., во <hn>:<mN> <at> <zt>
mk|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> г., во <hN>:<mN>:<sN>
mk|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> г., во <hN>:<mN> <zt>
mk|EEEEdh|<Et>, <dn>, <hn> <at>
mk|EEEEdhm|<Et>, <dn>, <hn>:<mN> <at>
mk|EEEEdHm|<Et>, <dn>, <hN>:<mN>
mk|EEEEdhmz|<Et>, <dn>, <hn>:<mN> <at> <zt>
mk|EEEEdHms|<Et>, <dn>, <hN>:<mN>:<sN>
mk|EEEEdHmzzzz|<Et>, <dn>, <hN>:<mN> <zt>
ms|d|<dn>
ms|E|<Et>
ms|Ed|<dn> <Et>
ms|EEEE|<Et>
ms|EEEEd|<dn> <Et>
ms|M|<Mn>
ms|Md|<dn>-<Mn>
ms|ME|<Mn> <Et>
ms|MEd|<Et>, <dn>-<Mn>
ms|MEEEE|<Mn> <Et>
ms|MEEEEd|<Et>, <dn>-<Mn>
ms|MM|<MN>
ms|MMd|<dn>/<MN>
ms|MME|<Et> (bulan: <MN>)
ms|MMEd|<Et>, <dn>-<MN>
ms|MMEEEE|<MN> <Et>
ms|MMEEEEd|<Et>, <dn>-<MN>
ms|MMM|<Mt>
ms|MMMd|<dn> <Mt>
ms|MMME|<Mt> <Et>
ms|MMMEd|<Et>, <dn> <Mt>
ms|MMMEEEE|<Mt> <Et>
ms|MMMEEEEd|<Et>, <dn> <Mt>
ms|MMMM|<Mt>
ms|MMMMd|<dn> <Mt>
ms|MMMME|<Et> (bulan: <Mt>)
ms|MMMMEd|<Et>, <dn> <Mt>
ms|MMMMEEEE|<Mt> <Et>
ms|MMMMEEEEd|<Et>, <dn> <Mt>
ms|y|<yn>
ms|yd|<yn> (hari: <dn>)
ms|yE|<yn> <Et>
ms|yEd|<yn> <dn> <Et>
ms|yEEEE|<yn> <Et>
ms|yEEEEd|<yn> <dn> <Et>
ms|yM|<Mn>-<yn>
ms|yMd|<dn>/<Mn>/<yn>
ms|yME|<Mn>-<yn> <Et>
ms|yMEd|<Et>, <dn>/<Mn>/<yn>
ms|yMEEEE|<Mn>-<yn> <Et>
ms|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
ms|yMM|<MN>-<yn>
ms|yMMd|<dn>/<MN>/<yn>
ms|yMME|<MN>-<yn> <Et>
ms|yMMEd|<Et>, <dn>/<MN>/<yn>
ms|yMMEEEE|<MN>-<yn> <Et>
ms|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
ms|yMMM|<Mt> <yn>
ms|yMMMd|<dn> <Mt> <yn>
ms|yMMME|<Mt> <yn> <Et>
ms|yMMMEd|<Et>, <dn> <Mt> <yn>
ms|yMMMEEEE|<Mt> <yn> <Et>
ms|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
ms|yMMMM|<Mt> <yn>
ms|yMMMMd|<dn> <Mt> <yn>
ms|yMMMME|<Mt> <yn> <Et>
ms|yMMMMEd|<Et>, <dn> <Mt> <yn>
ms|yMMMMEEEE|<Mt> <yn> <Et>
ms|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
ms|Gy|<yn> <Gt>
ms|GyMMMd|<dn> <Mt> <yn> <Gt>
ms|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
ms|GyMd|<Gt> <yn>-<MN>-<dN>
ms|s|<sn>
ms|sz|<sn> <zt>
ms|szzzz|<sn> <zt>
ms|m|<mn>
ms|mz|<mn> <zt>
ms|mzzzz|<mn> <zt>
ms|ms|<mN>:<sN>
ms|msz|<mN>:<sN> <zt>
ms|mszzzz|<mN>:<sN> <zt>
ms|h|<hn> <at>
ms|hz|<hn> <at> <zt>
ms|hzzzz|<hn> <at> <zt>
ms|hs|<hn> <at> (saat: <sn>)
ms|hsz|<hn> <at> <zt> (saat: <sn>)
ms|hszzzz|<hn> <at> <zt> (saat: <sn>)
ms|hm|<hn>:<mN> <at>
ms|hmz|<hn>:<mN> <at> <zt>
ms|hmzzzz|<hn>:<mN> <at> <zt>
ms|hms|<hn>:<mN>:<sN> <at>
ms|hmsz|<hn>:<mN>:<sN> <at> <zt>
ms|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
ms|H|<hN>
ms|Hz|<hN>h <zt>
ms|Hzzzz|<hN>h <zt>
ms|Hs|<hN> (saat: <sn>)
ms|Hsz|<hN>h <zt> (saat: <sn>)
ms|Hszzzz|<hN>h <zt> (saat: <sn>)
ms|Hm|<hN>:<mN>
ms|Hmz|<hN>:<mN> <zt>
ms|Hmzzzz|<hN>:<mN> <zt>
ms|Hms|<hN>:<mN>:<sN>
ms|Hmsz|<hN>:<mN>:<sN> <zt>
ms|Hmszzzz|<hN>:<mN>:<sN> <zt>
ms|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
ms|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
ms|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
ms|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
ms|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
ms|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
ms|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
ms|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
ms|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
ms|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
ms|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
ms|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
ms|yMdh|<dn>/<Mn>/<yn>, <hn> <at>
ms|yMdhm|<dn>/<Mn>/<yn>, <hn>:<mN> <at>
ms|yMdHm|<dn>/<Mn>/<yn>, <hN>:<mN>
ms|yMdhmz|<dn>/<Mn>/<yn>, <hn>:<mN> <at> <zt>
ms|yMdHms|<dn>/<Mn>/<yn>, <hN>:<mN>:<sN>
ms|yMdHmzzzz|<dn>/<Mn>/<yn>, <hN>:<mN> <zt>
ms|Eh|<Et> <hn> <at>
ms|Ehm|<Et> <hn>:<mN> <at>
ms|EHm|<Et> <hN>:<mN>
ms|Ehmz|<Et>, <hn>:<mN> <at> <zt>
ms|EHms|<Et> <hN>:<mN>:<sN>
ms|EHmzzzz|<Et>, <hN>:<mN> <zt>
ms|dh|<dn>, <hn> <at>
ms|dhm|<dn>, <hn>:<mN> <at>
ms|dHm|<dn>, <hN>:<mN>
ms|dhmz|<dn>, <hn>:<mN> <at> <zt>
ms|dHms|<dn>, <hN>:<mN>:<sN>
ms|dHmzzzz|<dn>, <hN>:<mN> <zt>
ms|MMMMdh|<dn> <Mt> pada <hn> <at>
ms|MMMMdhm|<dn> <Mt> pada <hn>:<mN> <at>
ms|MMMMdHm|<dn> <Mt> pada <hN>:<mN>
ms|MMMMdhmz|<dn> <Mt> pada <hn>:<mN> <at> <zt>
ms|MMMMdHms|<dn> <Mt> pada <hN>:<mN>:<sN>
ms|MMMMdHmzzzz|<dn> <Mt> pada <hN>:<mN> <zt>
ms|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> pada <hn> <at>
ms|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> pada <hn>:<mN> <at>
ms|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> pada <hN>:<mN>
ms|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> pada <hn>:<mN> <at> <zt>
ms|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> pada <hN>:<mN>:<sN>
ms|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> pada <hN>:<mN> <zt>
ms|EEEEdh|<dn> <Et>, <hn> <at>
ms|EEEEdhm|<dn> <Et>, <hn>:<mN> <at>
ms|EEEEdHm|<dn> <Et>, <hN>:<mN>
ms|EEEEdhmz|<dn> <Et>, <hn>:<mN> <at> <zt>
ms|EEEEdHms|<dn> <Et>, <hN>:<mN>:<sN>
ms|EEEEdHmzzzz|<dn> <Et>, <hN>:<mN> <zt>
nb|d|<dn>.
nb|E|<Et>
nb|Ed|<Et> <dn>.
nb|EEEE|<Et>
nb|EEEEd|<Et> <dn>.
nb|M|<Mn>.
nb|Md|<dn>.<Mn>.
nb|ME|<Mn>. <Et>
nb|MEd|<Et> <dn>.<Mn>.
nb|MEEEE|<Mn>. <Et>
nb|MEEEEd|<Et> <dn>.<Mn>.
nb|MM|<MN>.
nb|MMd|<dn>.<Mn>.
nb|MME|<Et> (måned: <MN>.)
nb|MMEd|<Et> <dn>.<MN>.
nb|MMEEEE|<MN>. <Et>
nb|MMEEEEd|<Et> <dn>.<MN>.
nb|MMM|<Mt>
nb|MMMd|<dn>. <Mt>
nb|MMME|<Mt> <Et>
nb|MMMEd|<Et> <dn>. <Mt>
nb|MMMEEEE|<Mt> <Et>
nb|MMMEEEEd|<Et> <dn>. <Mt>
nb|MMMM|<Mt>
nb|MMMMd|<dn>. <Mt>
nb|MMMME|<Et> (måned: <Mt>)
nb|MMMMEd|<Et> <dn>. <Mt>
nb|MMMMEEEE|<Mt> <Et>
nb|MMMMEEEEd|<Et> <dn>. <Mt>
nb|y|<yn>
nb|yd|<yn> (dag: <dn>.)
nb|yE|<yn> <Et>
nb|yEd|<yn> <Et> <dn>.
nb|yEEEE|<yn> <Et>
nb|yEEEEd|<yn> <Et> <dn>.
nb|yM|<Mn>.<yn>
nb|yMd|<dn>.<Mn>.<yn>
nb|yME|<Mn>.<yn> <Et>
nb|yMEd|<Et> <dn>.<Mn>.<yn>
nb|yMEEEE|<Mn>.<yn> <Et>
nb|yMEEEEd|<Et> <dn>.<Mn>.<yn>
nb|yMM|<MN>.<yn>
nb|yMMd|<dn>.<MN>.<yn>
nb|yMME|<MN>.<yn> <Et>
nb|yMMEd|<Et> <dn>.<MN>.<yn>
nb|yMMEEEE|<MN>.<yn> <Et>
nb|yMMEEEEd|<Et> <dn>.<MN>.<yn>
nb|yMMM|<Mt> <yn>
nb|yMMMd|<dn>. <Mt> <yn>
nb|yMMME|<Mt> <yn> <Et>
nb|yMMMEd|<Et> <dn>. <Mt> <yn>
nb|yMMMEEEE|<Mt> <yn> <Et>
nb|yMMMEEEEd|<Et> <dn>. <Mt> <yn>
nb|yMMMM|<Mt> <yn>
nb|yMMMMd|<dn>. <Mt> <yn>
nb|yMMMME|<Mt> <yn> <Et>
nb|yMMMMEd|<Et> <dn>. <Mt> <yn>
nb|yMMMMEEEE|<Mt> <yn> <Et>
nb|yMMMMEEEEd|<Et> <dn>. <Mt> <yn>
nb|Gy|<yn> <Gt>
nb|GyMMMd|<dn>. <Mt> <yn> <Gt>
nb|GyMMMMEEEEd|<Et> <dn>. <Mt> <yn> <Gt>
nb|GyMd|<dn>.<Mn>.<yn> <Gt>
nb|s|<sn>
nb|sz|<sn> <zt>
nb|szzzz|<sn> <zt>
nb|m|<mn>
nb|mz|<mn> <zt>
nb|mzzzz|<mn> <zt>
nb|ms|<mN>:<sN>
nb|msz|<mN>:<sN> <zt>
nb|mszzzz|<mN>:<sN> <zt>
nb|h|<hn> <at>
nb|hz|<hn> <at> <zt>
nb|hzzzz|<hn> <at> <zt>
nb|hs|<hn> <at> (sekund: <sn>)
nb|hsz|<hn> <at> <zt> (sekund: <sn>)
nb|hszzzz|<hn> <at> <zt> (sekund: <sn>)
nb|hm|<hn>:<mN> <at>
nb|hmz|<hn>:<mN> <at> <zt>
nb|hmzzzz|<hn>:<mN> <at> <zt>
nb|hms|<hn>:<mN>:<sN> <at>
nb|hmsz|<hn>:<mN>:<sN> <at> <zt>
nb|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
nb|H|<hN>
nb|Hz|<hN> <zt>
nb|Hzzzz|<hN> <zt>
nb|Hs|<hN> (sekund: <sn>)
nb|Hsz|<hN> <zt> (sekund: <sn>)
nb|Hszzzz|<hN> <zt> (sekund: <sn>)
nb|Hm|<hN>:<mN>
nb|Hmz|<hN>:<mN> <zt>
nb|Hmzzzz|<hN>:<mN> <zt>
nb|Hms|<hN>:<mN>:<sN>
nb|Hmsz|<hn>:<mN>:<sN> <zt>
nb|Hmszzzz|<hn>:<mN>:<sN> <zt>
nb|yMMMdh|<dn>. <Mt> <yn>, <hn> <at>
nb|yMMMdhm|<dn>. <Mt> <yn>, <hn>:<mN> <at>
nb|yMMMdHm|<dn>. <Mt> <yn>, <hN>:<mN>
nb|yMMMdhmz|<dn>. <Mt> <yn>, <hn>:<mN> <at> <zt>
nb|yMMMdHms|<dn>. <Mt> <yn>, <hN>:<mN>:<sN>
nb|yMMMdHmzzzz|<dn>. <Mt> <yn>, <hN>:<mN> <zt>
nb|MMMEdh|<Et> <dn>. <Mt>, <hn> <at>
nb|MMMEdhm|<Et> <dn>. <Mt>, <hn>:<mN> <at>
nb|MMMEdHm|<Et> <dn>. <Mt>, <hN>:<mN>
nb|MMMEdhmz|<Et> <dn>. <Mt>, <hn>:<mN> <at> <zt>
nb|MMMEdHms|<Et> <dn>. <Mt>, <hN>:<mN>:<sN>
nb|MMMEdHmzzzz|<Et> <dn>. <Mt>, <hN>:<mN> <zt>
nb|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
nb|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
nb|yMdHm|<dn>.<Mn>.<yn>, <hN>:<mN>
nb|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at> <zt>
nb|yMdHms|<dn>.<Mn>.<yn>, <hN>:<mN>:<sN>
nb|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>:<mN> <zt>
nb|Eh|<Et> <hn> <at>
nb|Ehm|<Et> <hn>:<mN> <at>
nb|EHm|<Et> kl. <hN>:<mN>
nb|Ehmz|<Et>, <hn>:<mN> <at> <zt>
nb|EHms|<Et> kl. <hN>:<mN>:<sN>
nb|EHmzzzz|<Et>, <hN>:<mN> <zt>
nb|dh|<dn>., <hn> <at>
nb|dhm|<dn>., <hn>:<mN> <at>
nb|dHm|<dn>., <hN>:<mN>
nb|dhmz|<dn>., <hn>:<mN> <at> <zt>
nb|dHms|<dn>., <hN>:<mN>:<sN>
nb|dHmzzzz|<dn>., <hN>:<mN> <zt>
nb|MMMMdh|<dn>. <Mt> kl. <hn> <at>
nb|MMMMdhm|<dn>. <Mt> kl. <hn>:<mN> <at>
nb|MMMMdHm|<dn>. <Mt> kl. <hN>:<mN>
nb|MMMMdhmz|<dn>. <Mt> kl. <hn>:<mN> <at> <zt>
nb|MMMMdHms|<dn>. <Mt> kl. <hN>:<mN>:<sN>
nb|MMMMdHmzzzz|<dn>. <Mt> kl. <hN>:<mN> <zt>
nb|yMMMMEEEEdh|<Et> <dn>. <Mt> <yn> kl. <hn> <at>
nb|yMMMMEEEEdhm|<Et> <dn>. <Mt> <yn> kl. <hn>:<mN> <at>
nb|yMMMMEEEEdHm|<Et> <dn>. <Mt> <yn> kl. <hN>:<mN>
nb|yMMMMEEEEdhmz|<Et> <dn>. <Mt> <yn> kl. <hn>:<mN> <at> <zt>
nb|yMMMMEEEEdHms|<Et> <dn>. <Mt> <yn> kl. <hN>:<mN>:<sN>
nb|yMMMMEEEEdHmzzzz|<Et> <dn>. <Mt> <yn> kl. <hN>:<mN> <zt>
nb|EEEEdh|<Et> <dn>., <hn> <at>
nb|EEEEdhm|<Et> <dn>., <hn>:<mN> <at>
nb|EEEEdHm|<Et> <dn>., <hN>:<mN>
nb|EEEEdhmz|<Et> <dn>., <hn>:<mN> <at> <zt>
nb|EEEEdHms|<Et> <dn>., <hN>:<mN>:<sN>
nb|EEEEdHmzzzz|<Et> <dn>., <hN>:<mN> <zt>
nl|d|<dn>
nl|E|<Et>
nl|Ed|<Et> <dn>
nl|EEEE|<Et>
nl|EEEEd|<Et> <dn>
nl|M|<Mn>
nl|Md|<dn>-<Mn>
nl|ME|<Mn> <Et>
nl|MEd|<Et> <dn>-<Mn>
nl|MEEEE|<Mn> <Et>
nl|MEEEEd|<Et> <dn>-<Mn>
nl|MM|<MN>
nl|MMd|<dn>-<MN>
nl|MME|<Et> (maand: <MN>)
nl|MMEd|<Et> <dn>-<MN>
nl|MMEEEE|<MN> <Et>
nl|MMEEEEd|<Et> <dn>-<MN>
nl|MMM|<Mt>
nl|MMMd|<dn> <Mt>
nl|MMME|<Mt> <Et>
nl|MMMEd|<Et> <dn> <Mt>
nl|MMMEEEE|<Mt> <Et>
nl|MMMEEEEd|<Et> <dn> <Mt>
nl|MMMM|<Mt>
nl|MMMMd|<dn> <Mt>
nl|MMMME|<Et> (maand: <Mt>)
nl|MMMMEd|<Et> <dn> <Mt>
nl|MMMMEEEE|<Mt> <Et>
nl|MMMMEEEEd|<Et> <dn> <Mt>
nl|y|<yn>
nl|yd|<yn> (dag: <dn>)
nl|yE|<yn> <Et>
nl|yEd|<yn> <Et> <dn>
nl|yEEEE|<yn> <Et>
nl|yEEEEd|<yn> <Et> <dn>
nl|yM|<Mn>-<yn>
nl|yMd|<dn>-<Mn>-<yn>
nl|yME|<Mn>-<yn> <Et>
nl|yMEd|<Et> <dn>-<Mn>-<yn>
nl|yMEEEE|<Mn>-<yn> <Et>
nl|yMEEEEd|<Et> <dn>-<Mn>-<yn>
nl|yMM|<MN>-<yn>
nl|yMMd|<dn>-<MN>-<yn>
nl|yMME|<MN>-<yn> <Et>
nl|yMMEd|<Et> <dn>-<MN>-<yn>
nl|yMMEEEE|<MN>-<yn> <Et>
nl|yMMEEEEd|<Et> <dn>-<MN>-<yn>
nl|yMMM|<Mt> <yn>
nl|yMMMd|<dn> <Mt> <yn>
nl|yMMME|<Mt> <yn> <Et>
nl|yMMMEd|<Et> <dn> <Mt> <yn>
nl|yMMMEEEE|<Mt> <yn> <Et>
nl|yMMMEEEEd|<Et> <dn> <Mt> <yn>
nl|yMMMM|<Mt> <yn>
nl|yMMMMd|<dn> <Mt> <yn>
nl|yMMMME|<Mt> <yn> <Et>
nl|yMMMMEd|<Et> <dn> <Mt> <yn>
nl|yMMMMEEEE|<Mt> <yn> <Et>
nl|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
nl|Gy|<yn> <Gt>
nl|GyMMMd|<dn> <Mt> <yn> <Gt>
nl|GyMMMMEEEEd|<Et> <dn> <Mt> <yn> <Gt>
nl|GyMd|<dn>/<Mn>/<yn> <Gt>
nl|s|<sn>
nl|sz|<sn> <zt>
nl|szzzz|<sn> <zt>
nl|m|<mn>
nl|mz|<mn> <zt>
nl|mzzzz|<mn> <zt>
nl|ms|<mN>:<sN>
nl|msz|<mN>:<sN> <zt>
nl|mszzzz|<mN>:<sN> <zt>
nl|h|<hn> <at>
nl|hz|<hn> <at> <zt>
nl|hzzzz|<hn> <at> <zt>
nl|hs|<hn> <at> (seconde: <sn>)
nl|hsz|<hn> <at> <zt> (seconde: <sn>)
nl|hszzzz|<hn> <at> <zt> (seconde: <sn>)
nl|hm|<hn>:<mN> <at>
nl|hmz|<hn>:<mN> <at> <zt>
nl|hmzzzz|<hn>:<mN> <at> <zt>
nl|hms|<hn>:<mN>:<sN> <at>
nl|hmsz|<hn>:<mN>:<sN> <at> <zt>
nl|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
nl|H|<hN>
nl|Hz|<hN>h <zt>
nl|Hzzzz|<hN>h <zt>
nl|Hs|<hN> (seconde: <sn>)
nl|Hsz|<hN>h <zt> (seconde: <sn>)
nl|Hszzzz|<hN>h <zt> (seconde: <sn>)
nl|Hm|<hN>:<mN>
nl|Hmz|<hN>:<mN> <zt>
nl|Hmzzzz|<hN>:<mN> <zt>
nl|Hms|<hN>:<mN>:<sN>
nl|Hmsz|<hn>:<mN>:<sN> <zt>
nl|Hmszzzz|<hn>:<mN>:<sN> <zt>
nl|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
nl|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
nl|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
nl|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
nl|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
nl|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
nl|MMMEdh|<Et> <dn> <Mt>, <hn> <at>
nl|MMMEdhm|<Et> <dn> <Mt>, <hn>:<mN> <at>
nl|MMMEdHm|<Et> <dn> <Mt>, <hN>:<mN>
nl|MMMEdhmz|<Et> <dn> <Mt>, <hn>:<mN> <at> <zt>
nl|MMMEdHms|<Et> <dn> <Mt>, <hN>:<mN>:<sN>
nl|MMMEdHmzzzz|<Et> <dn> <Mt>, <hN>:<mN> <zt>
nl|yMdh|<dn>-<Mn>-<yn>, <hn> <at>
nl|yMdhm|<dn>-<Mn>-<yn>, <hn>:<mN> <at>
nl|yMdHm|<dn>-<Mn>-<yn>, <hN>:<mN>
nl|yMdhmz|<dn>-<Mn>-<yn>, <hn>:<mN> <at> <zt>
nl|yMdHms|<dn>-<Mn>-<yn>, <hN>:<mN>:<sN>
nl|yMdHmzzzz|<dn>-<Mn>-<yn>, <hN>:<mN> <zt>
nl|Eh|<Et> <hn> <at>
nl|Ehm|<Et> <hn>:<mN> <at>
nl|EHm|<Et> <hN>:<mN>
nl|Ehmz|<Et>, <hn>:<mN> <at> <zt>
nl|EHms|<Et> <hN>:<mN>:<sN>
nl|EHmzzzz|<Et>, <hN>:<mN> <zt>
nl|dh|<dn>, <hn> <at>
nl|dhm|<dn>, <hn>:<mN> <at>
nl|dHm|<dn>, <hN>:<mN>
nl|dhmz|<dn>, <hn>:<mN> <at> <zt>
nl|dHms|<dn>, <hN>:<mN>:<sN>
nl|dHmzzzz|<dn>, <hN>:<mN> <zt>
nl|MMMMdh|<dn> <Mt> om <hn> <at>
nl|MMMMdhm|<dn> <Mt> om <hn>:<mN> <at>
nl|MMMMdHm|<dn> <Mt> om <hN>:<mN>
nl|MMMMdhmz|<dn> <Mt> om <hn>:<mN> <at> <zt>
nl|MMMMdHms|<dn> <Mt> om <hN>:<mN>:<sN>
nl|MMMMdHmzzzz|<dn> <Mt> om <hN>:<mN> <zt>
nl|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> om <hn> <at>
nl|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> om <hn>:<mN> <at>
nl|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> om <hN>:<mN>
nl|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> om <hn>:<mN> <at> <zt>
nl|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> om <hN>:<mN>:<sN>
nl|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> om <hN>:<mN> <zt>
nl|EEEEdh|<Et> <dn>, <hn> <at>
nl|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
nl|EEEEdHm|<Et> <dn>, <hN>:<mN>
nl|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
nl|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
nl|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
nn|d|<dn>.
nn|E|<Et>
nn|Ed|<Et> <dn>.
nn|EEEE|<Et>
nn|EEEEd|<Et> <dn>.
nn|M|<Mn>.
nn|Md|<dn>.<Mn>.
nn|ME|<Mn>. <Et>
nn|MEd|<Et> <dn>.<Mn>.
nn|MEEEE|<Mn>. <Et>
nn|MEEEEd|<Et> <dn>.<Mn>.
nn|MM|<MN>.
nn|MMd|<dn>.<Mn>.
nn|MME|<Et> (månad: <MN>.)
nn|MMEd|<Et> <dn>.<MN>.
nn|MMEEEE|<MN>. <Et>
nn|MMEEEEd|<Et> <dn>.<MN>.
nn|MMM|<Mt>
nn|MMMd|<dn>. <Mt>
nn|MMME|<Mt> <Et>
nn|MMMEd|<Et> <dn>. <Mt>
nn|MMMEEEE|<Mt> <Et>
nn|MMMEEEEd|<Et> <dn>. <Mt>
nn|MMMM|<Mt>
nn|MMMMd|<dn>. <Mt>
nn|MMMME|<Et> (månad: <Mt>)
nn|MMMMEd|<Et> <dn>. <Mt>
nn|MMMMEEEE|<Mt> <Et>
nn|MMMMEEEEd|<Et> <dn>. <Mt>
nn|y|<yn>
nn|yd|<yn> (dag: <dn>.)
nn|yE|<yn> <Et>
nn|yEd|<yn> <Et> <dn>.
nn|yEEEE|<yn> <Et>
nn|yEEEEd|<yn> <Et> <dn>.
nn|yM|<Mn>.<yn>
nn|yMd|<dn>.<Mn>.<yn>
nn|yME|<Mn>.<yn> <Et>
nn|yMEd|<Et> <dn>.<Mn>.<yn>
nn|yMEEEE|<Mn>.<yn> <Et>
nn|yMEEEEd|<Et> <dn>.<Mn>.<yn>
nn|yMM|<MN>.<yn>
nn|yMMd|<dn>.<MN>.<yn>
nn|yMME|<MN>.<yn> <Et>
nn|yMMEd|<Et> <dn>.<MN>.<yn>
nn|yMMEEEE|<MN>.<yn> <Et>
nn|yMMEEEEd|<Et> <dn>.<MN>.<yn>
nn|yMMM|<Mt> <yn>
nn|yMMMd|<dn>. <Mt> <yn>
nn|yMMME|<Mt> <yn> <Et>
nn|yMMMEd|<Et> <dn>. <Mt> <yn>
nn|yMMMEEEE|<Mt> <yn> <Et>
nn|yMMMEEEEd|<Et> <dn>. <Mt> <yn>
nn|yMMMM|<Mt> <yn>
nn|yMMMMd|<dn>. <Mt> <yn>
nn|yMMMME|<Mt> <yn> <Et>
nn|yMMMMEd|<Et> <dn>. <Mt> <yn>
nn|yMMMMEEEE|<Mt> <yn> <Et>
nn|yMMMMEEEEd|<Et> <dn>. <Mt> <yn>
nn|Gy|<yn> <Gt>
nn|GyMMMd|<dn>. <Mt> <yn> <Gt>
nn|GyMMMMEEEEd|<Et> <dn>. <Mt> <yn> <Gt>
nn|GyMd|<dn>.<Mn>.<yn> <Gt>
nn|s|<sn>
nn|sz|<sn> <zt>
nn|szzzz|<sn> <zt>
nn|m|<mn>
nn|mz|<mn> <zt>
nn|mzzzz|<mn> <zt>
nn|ms|<mN>:<sN>
nn|msz|<mN>:<sN> <zt>
nn|mszzzz|<mN>:<sN> <zt>
nn|h|<hn> <at>
nn|hz|<hn> <at> <zt>
nn|hzzzz|<hn> <at> <zt>
nn|hs|<hn> <at> (sekund: <sn>)
nn|hsz|<hn> <at> <zt> (sekund: <sn>)
nn|hszzzz|<hn> <at> <zt> (sekund: <sn>)
nn|hm|<hn>:<mN> <at>
nn|hmz|<hn>:<mN> <at> <zt>
nn|hmzzzz|<hn>:<mN> <at> <zt>
nn|hms|<hn>:<mN>:<sN> <at>
nn|hmsz|<hn>:<mN>:<sN> <at> <zt>
nn|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
nn|H|<hN>
nn|Hz|<hN> <zt>
nn|Hzzzz|<hN> <zt>
nn|Hs|<hN> (sekund: <sn>)
nn|Hsz|<hN> <zt> (sekund: <sn>)
nn|Hszzzz|<hN> <zt> (sekund: <sn>)
nn|Hm|<hN>:<mN>
nn|Hmz|<hN>:<mN> <zt>
nn|Hmzzzz|<hN>:<mN> <zt>
nn|Hms|<hN>:<mN>:<sN>
nn|Hmsz|<hn>:<mN>:<sN> <zt>
nn|Hmszzzz|<hn>:<mN>:<sN> <zt>
nn|yMMMdh|<dn>. <Mt> <yn>, <hn> <at>
nn|yMMMdhm|<dn>. <Mt> <yn>, <hn>:<mN> <at>
nn|yMMMdHm|<dn>. <Mt> <yn>, <hN>:<mN>
nn|yMMMdhmz|<dn>. <Mt> <yn>, <hn>:<mN> <at> <zt>
nn|yMMMdHms|<dn>. <Mt> <yn>, <hN>:<mN>:<sN>
nn|yMMMdHmzzzz|<dn>. <Mt> <yn>, <hN>:<mN> <zt>
nn|MMMEdh|<Et> <dn>. <Mt>, <hn> <at>
nn|MMMEdhm|<Et> <dn>. <Mt>, <hn>:<mN> <at>
nn|MMMEdHm|<Et> <dn>. <Mt>, <hN>:<mN>
nn|MMMEdhmz|<Et> <dn>. <Mt>, <hn>:<mN> <at> <zt>
nn|MMMEdHms|<Et> <dn>. <Mt>, <hN>:<mN>:<sN>
nn|MMMEdHmzzzz|<Et> <dn>. <Mt>, <hN>:<mN> <zt>
nn|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
nn|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
nn|yMdHm|<dn>.<Mn>.<yn>, <hN>:<mN>
nn|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at> <zt>
nn|yMdHms|<dn>.<Mn>.<yn>, <hN>:<mN>:<sN>
nn|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>:<mN> <zt>
nn|Eh|<Et> <hn> <at>
nn|Ehm|<Et> <hn>:<mN> <at>
nn|EHm|<Et> <hN>:<mN>
nn|Ehmz|<Et>, <hn>:<mN> <at> <zt>
nn|EHms|<Et> <hN>:<mN>:<sN>
nn|EHmzzzz|<Et>, <hN>:<mN> <zt>
nn|dh|<dn>., <hn> <at>
nn|dhm|<dn>., <hn>:<mN> <at>
nn|dHm|<dn>., <hN>:<mN>
nn|dhmz|<dn>., <hn>:<mN> <at> <zt>
nn|dHms|<dn>., <hN>:<mN>:<sN>
nn|dHmzzzz|<dn>., <hN>:<mN> <zt>
nn|MMMMdh|<dn>. <Mt> kl. <hn> <at>
nn|MMMMdhm|<dn>. <Mt> kl. <hn>:<mN> <at>
nn|MMMMdHm|<dn>. <Mt> kl. <hN>:<mN>
nn|MMMMdhmz|<dn>. <Mt> kl. <hn>:<mN> <at> <zt>
nn|MMMMdHms|<dn>. <Mt> kl. <hN>:<mN>:<sN>
nn|MMMMdHmzzzz|<dn>. <Mt> kl. <hN>:<mN> <zt>
nn|yMMMMEEEEdh|<Et> <dn>. <Mt> <yn> kl. <hn> <at>
nn|yMMMMEEEEdhm|<Et> <dn>. <Mt> <yn> kl. <hn>:<mN> <at>
nn|yMMMMEEEEdHm|<Et> <dn>. <Mt> <yn> kl. <hN>:<mN>
nn|yMMMMEEEEdhmz|<Et> <dn>. <Mt> <yn> kl. <hn>:<mN> <at> <zt>
nn|yMMMMEEEEdHms|<Et> <dn>. <Mt> <yn> kl. <hN>:<mN>:<sN>
nn|yMMMMEEEEdHmzzzz|<Et> <dn>. <Mt> <yn> kl. <hN>:<mN> <zt>
nn|EEEEdh|<Et> <dn>., <hn> <at>
nn|EEEEdhm|<Et> <dn>., <hn>:<mN> <at>
nn|EEEEdHm|<Et> <dn>., <hN>:<mN>
nn|EEEEdhmz|<Et> <dn>., <hn>:<mN> <at> <zt>
nn|EEEEdHms|<Et> <dn>., <hN>:<mN>:<sN>
nn|EEEEdHmzzzz|<Et> <dn>., <hN>:<mN> <zt>
no|d|<dn>.
no|E|<Et>
no|Ed|<Et> <dn>.
no|EEEE|<Et>
no|EEEEd|<Et> <dn>.
no|M|<Mn>.
no|Md|<dn>.<Mn>.
no|ME|<Mn>. <Et>
no|MEd|<Et> <dn>.<Mn>.
no|MEEEE|<Mn>. <Et>
no|MEEEEd|<Et> <dn>.<Mn>.
no|MM|<MN>.
no|MMd|<dn>.<Mn>.
no|MME|<Et> (måned: <MN>.)
no|MMEd|<Et> <dn>.<MN>.
no|MMEEEE|<MN>. <Et>
no|MMEEEEd|<Et> <dn>.<MN>.
no|MMM|<Mt>
no|MMMd|<dn>. <Mt>
no|MMME|<Mt> <Et>
no|MMMEd|<Et> <dn>. <Mt>
no|MMMEEEE|<Mt> <Et>
no|MMMEEEEd|<Et> <dn>. <Mt>
no|MMMM|<Mt>
no|MMMMd|<dn>. <Mt>
no|MMMME|<Et> (måned: <Mt>)
no|MMMMEd|<Et> <dn>. <Mt>
no|MMMMEEEE|<Mt> <Et>
no|MMMMEEEEd|<Et> <dn>. <Mt>
no|y|<yn>
no|yd|<yn> (dag: <dn>.)
no|yE|<yn> <Et>
no|yEd|<yn> <Et> <dn>.
no|yEEEE|<yn> <Et>
no|yEEEEd|<yn> <Et> <dn>.
no|yM|<Mn>.<yn>
no|yMd|<dn>.<Mn>.<yn>
no|yME|<Mn>.<yn> <Et>
no|yMEd|<Et> <dn>.<Mn>.<yn>
no|yMEEEE|<Mn>.<yn> <Et>
no|yMEEEEd|<Et> <dn>.<Mn>.<yn>
no|yMM|<MN>.<yn>
no|yMMd|<dn>.<MN>.<yn>
no|yMME|<MN>.<yn> <Et>
no|yMMEd|<Et> <dn>.<MN>.<yn>
no|yMMEEEE|<MN>.<yn> <Et>
no|yMMEEEEd|<Et> <dn>.<MN>.<yn>
no|yMMM|<Mt> <yn>
no|yMMMd|<dn>. <Mt> <yn>
no|yMMME|<Mt> <yn> <Et>
no|yMMMEd|<Et> <dn>. <Mt> <yn>
no|yMMMEEEE|<Mt> <yn> <Et>
no|yMMMEEEEd|<Et> <dn>. <Mt> <yn>
no|yMMMM|<Mt> <yn>
no|yMMMMd|<dn>. <Mt> <yn>
no|yMMMME|<Mt> <yn> <Et>
no|yMMMMEd|<Et> <dn>. <Mt> <yn>
no|yMMMMEEEE|<Mt> <yn> <Et>
no|yMMMMEEEEd|<Et> <dn>. <Mt> <yn>
no|Gy|<yn> <Gt>
no|GyMMMd|<dn>. <Mt> <yn> <Gt>
no|GyMMMMEEEEd|<Et> <dn>. <Mt> <yn> <Gt>
no|GyMd|<dn>.<Mn>.<yn> <Gt>
no|s|<sn>
no|sz|<sn> <zt>
no|szzzz|<sn> <zt>
no|m|<mn>
no|mz|<mn> <zt>
no|mzzzz|<mn> <zt>
no|ms|<mN>:<sN>
no|msz|<mN>:<sN> <zt>
no|mszzzz|<mN>:<sN> <zt>
no|h|<hn> <at>
no|hz|<hn> <at> <zt>
no|hzzzz|<hn> <at> <zt>
no|hs|<hn> <at> (sekund: <sn>)
no|hsz|<hn> <at> <zt> (sekund: <sn>)
no|hszzzz|<hn> <at> <zt> (sekund: <sn>)
no|hm|<hn>:<mN> <at>
no|hmz|<hn>:<mN> <at> <zt>
no|hmzzzz|<hn>:<mN> <at> <zt>
no|hms|<hn>:<mN>:<sN> <at>
no|hmsz|<hn>:<mN>:<sN> <at> <zt>
no|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
no|H|<hN>
no|Hz|<hN> <zt>
no|Hzzzz|<hN> <zt>
no|Hs|<hN> (sekund: <sn>)
no|Hsz|<hN> <zt> (sekund: <sn>)
no|Hszzzz|<hN> <zt> (sekund: <sn>)
no|Hm|<hN>:<mN>
no|Hmz|<hN>:<mN> <zt>
no|Hmzzzz|<hN>:<mN> <zt>
no|Hms|<hN>:<mN>:<sN>
no|Hmsz|<hn>:<mN>:<sN> <zt>
no|Hmszzzz|<hn>:<mN>:<sN> <zt>
no|yMMMdh|<dn>. <Mt> <yn>, <hn> <at>
no|yMMMdhm|<dn>. <Mt> <yn>, <hn>:<mN> <at>
no|yMMMdHm|<dn>. <Mt> <yn>, <hN>:<mN>
no|yMMMdhmz|<dn>. <Mt> <yn>, <hn>:<mN> <at> <zt>
no|yMMMdHms|<dn>. <Mt> <yn>, <hN>:<mN>:<sN>
no|yMMMdHmzzzz|<dn>. <Mt> <yn>, <hN>:<mN> <zt>
no|MMMEdh|<Et> <dn>. <Mt>, <hn> <at>
no|MMMEdhm|<Et> <dn>. <Mt>, <hn>:<mN> <at>
no|MMMEdHm|<Et> <dn>. <Mt>, <hN>:<mN>
no|MMMEdhmz|<Et> <dn>. <Mt>, <hn>:<mN> <at> <zt>
no|MMMEdHms|<Et> <dn>. <Mt>, <hN>:<mN>:<sN>
no|MMMEdHmzzzz|<Et> <dn>. <Mt>, <hN>:<mN> <zt>
no|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
no|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
no|yMdHm|<dn>.<Mn>.<yn>, <hN>:<mN>
no|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at> <zt>
no|yMdHms|<dn>.<Mn>.<yn>, <hN>:<mN>:<sN>
no|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>:<mN> <zt>
no|Eh|<Et> <hn> <at>
no|Ehm|<Et> <hn>:<mN> <at>
no|EHm|<Et> kl. <hN>:<mN>
no|Ehmz|<Et>, <hn>:<mN> <at> <zt>
no|EHms|<Et> kl. <hN>:<mN>:<sN>
no|EHmzzzz|<Et>, <hN>:<mN> <zt>
no|dh|<dn>., <hn> <at>
no|dhm|<dn>., <hn>:<mN> <at>
no|dHm|<dn>., <hN>:<mN>
no|dhmz|<dn>., <hn>:<mN> <at> <zt>
no|dHms|<dn>., <hN>:<mN>:<sN>
no|dHmzzzz|<dn>., <hN>:<mN> <zt>
no|MMMMdh|<dn>. <Mt> kl. <hn> <at>
no|MMMMdhm|<dn>. <Mt> kl. <hn>:<mN> <at>
no|MMMMdHm|<dn>. <Mt> kl. <hN>:<mN>
no|MMMMdhmz|<dn>. <Mt> kl. <hn>:<mN> <at> <zt>
no|MMMMdHms|<dn>. <Mt> kl. <hN>:<mN>:<sN>
no|MMMMdHmzzzz|<dn>. <Mt> kl. <hN>:<mN> <zt>
no|yMMMMEEEEdh|<Et> <dn>. <Mt> <yn> kl. <hn> <at>
no|yMMMMEEEEdhm|<Et> <dn>. <Mt> <yn> kl. <hn>:<mN> <at>
no|yMMMMEEEEdHm|<Et> <dn>. <Mt> <yn> kl. <hN>:<mN>
no|yMMMMEEEEdhmz|<Et> <dn>. <Mt> <yn> kl. <hn>:<mN> <at> <zt>
no|yMMMMEEEEdHms|<Et> <dn>. <Mt> <yn> kl. <hN>:<mN>:<sN>
no|yMMMMEEEEdHmzzzz|<Et> <dn>. <Mt> <yn> kl. <hN>:<mN> <zt>
no|EEEEdh|<Et> <dn>., <hn> <at>
no|EEEEdhm|<Et> <dn>., <hn>:<mN> <at>
no|EEEEdHm|<Et> <dn>., <hN>:<mN>
no|EEEEdhmz|<Et> <dn>., <hn>:<mN> <at> <zt>
no|EEEEdHms|<Et> <dn>., <hN>:<mN>:<sN>
no|EEEEdHmzzzz|<Et> <dn>., <hN>:<mN> <zt>
pl|d|<dn>
pl|E|<Et>
pl|Ed|<Et>, <dn>
pl|EEEE|<Et>
pl|EEEEd|<Et>, <dn>
pl|M|<Mn>
pl|Md|<dn>.<MN>
pl|ME|<Mn> <Et>
pl|MEd|<Et>, <dn>.<MN>
pl|MEEEE|<Mn> <Et>
pl|MEEEEd|<Et>, <dn>.<MN>
pl|MM|<MN>
pl|MMd|<dn>.<MN>
pl|MME|<Et> (miesiąc: <MN>)
pl|MMEd|<Et>, <dn>.<MN>
pl|MMEEEE|<MN> <Et>
pl|MMEEEEd|<Et>, <dn>.<MN>
pl|MMM|<Mt>
pl|MMMd|<dn> <Mt>
pl|MMME|<Mt> <Et>
pl|MMMEd|<Et>, <dn> <Mt>
pl|MMMEEEE|<Mt> <Et>
pl|MMMEEEEd|<Et>, <dn> <Mt>
pl|MMMM|<Mt>
pl|MMMMd|<dn> <Mt>
pl|MMMME|<Et> (miesiąc: <Mt>)
pl|MMMMEd|<Et>, <dn> <Mt>
pl|MMMMEEEE|<Mt> <Et>
pl|MMMMEEEEd|<Et>, <dn> <Mt>
pl|y|<yn>
pl|yd|<yn> (dzień: <dn>)
pl|yE|<yn> <Et>
pl|yEd|<yn> <Et>, <dn>
pl|yEEEE|<yn> <Et>
pl|yEEEEd|<yn> <Et>, <dn>
pl|yM|<MN>.<yn>
pl|yMd|<dn>.<MN>.<yn>
pl|yME|<MN>.<yn> <Et>
pl|yMEd|<Et>, <dn>.<MN>.<yn>
pl|yMEEEE|<MN>.<yn> <Et>
pl|yMEEEEd|<Et>, <dn>.<MN>.<yn>
pl|yMM|<MN>.<yn>
pl|yMMd|<dn>.<MN>.<yn>
pl|yMME|<MN>.<yn> <Et>
pl|yMMEd|<Et>, <dn>.<MN>.<yn>
pl|yMMEEEE|<MN>.<yn> <Et>
pl|yMMEEEEd|<Et>, <dn>.<MN>.<yn>
pl|yMMM|<Mt> <yn>
pl|yMMMd|<dn> <Mt> <yn>
pl|yMMME|<Mt> <yn> <Et>
pl|yMMMEd|<Et>, <dn> <Mt> <yn>
pl|yMMMEEEE|<Mt> <yn> <Et>
pl|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
pl|yMMMM|<Mt> <yn>
pl|yMMMMd|<dn> <Mt> <yn>
pl|yMMMME|<Mt> <yn> <Et>
pl|yMMMMEd|<Et>, <dn> <Mt> <yn>
pl|yMMMMEEEE|<Mt> <yn> <Et>
pl|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
pl|Gy|<yn> <Gt>
pl|GyMMMd|<dn> <Mt> <yn> <Gt>
pl|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
pl|GyMd|<dn>.<MN>.<yn> <Gt>
pl|s|<sn>
pl|sz|<sn> <zt>
pl|szzzz|<sn> <zt>
pl|m|<mn>
pl|mz|<mn> <zt>
pl|mzzzz|<mn> <zt>
pl|ms|<mN>:<sN>
pl|msz|<mN>:<sN> <zt>
pl|mszzzz|<mN>:<sN> <zt>
pl|h|<hn> <at>
pl|hz|<hn> <at> <zt>
pl|hzzzz|<hn> <at> <zt>
pl|hs|<hn> <at> (sekunda: <sn>)
pl|hsz|<hn> <at> <zt> (sekunda: <sn>)
pl|hszzzz|<hn> <at> <zt> (sekunda: <sn>)
pl|hm|<hn>:<mN> <at>
pl|hmz|<hn>:<mN> <at> <zt>
pl|hmzzzz|<hn>:<mN> <at> <zt>
pl|hms|<hn>:<mN>:<sN> <at>
pl|hmsz|<hn>:<mN>:<sN> <at> <zt>
pl|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
pl|H|<hN>
pl|Hz|<hN>h <zt>
pl|Hzzzz|<hN>h <zt>
pl|Hs|<hN> (sekunda: <sn>)
pl|Hsz|<hN>h <zt> (sekunda: <sn>)
pl|Hszzzz|<hN>h <zt> (sekunda: <sn>)
pl|Hm|<hN>:<mN>
pl|Hmz|<hN>:<mN> <zt>
pl|Hmzzzz|<hN>:<mN> <zt>
pl|Hms|<hN>:<mN>:<sN>
pl|Hmsz|<hn>:<mN>:<sN> <zt>
pl|Hmszzzz|<hn>:<mN>:<sN> <zt>
pl|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
pl|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
pl|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
pl|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
pl|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
pl|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
pl|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
pl|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
pl|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
pl|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
pl|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
pl|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
pl|yMdh|<dn>.<MN>.<yn>, <hn> <at>
pl|yMdhm|<dn>.<MN>.<yn>, <hn>:<mN> <at>
pl|yMdHm|<dn>.<MN>.<yn>, <hN>:<mN>
pl|yMdhmz|<dn>.<MN>.<yn>, <hn>:<mN> <at> <zt>
pl|yMdHms|<dn>.<MN>.<yn>, <hN>:<mN>:<sN>
pl|yMdHmzzzz|<dn>.<MN>.<yn>, <hN>:<mN> <zt>
pl|Eh|<Et> <hn> <at>
pl|Ehm|<Et>, <hn>:<mN> <at>
pl|EHm|<Et>, <hN>:<mN>
pl|Ehmz|<Et>, <hn>:<mN> <at> <zt>
pl|EHms|<Et>, <hN>:<mN>:<sN>
pl|EHmzzzz|<Et>, <hN>:<mN> <zt>
pl|dh|<dn>, <hn> <at>
pl|dhm|<dn>, <hn>:<mN> <at>
pl|dHm|<dn>, <hN>:<mN>
pl|dhmz|<dn>, <hn>:<mN> <at> <zt>
pl|dHms|<dn>, <hN>:<mN>:<sN>
pl|dHmzzzz|<dn>, <hN>:<mN> <zt>
pl|MMMMdh|<dn> <Mt> <hn> <at>
pl|MMMMdhm|<dn> <Mt> <hn>:<mN> <at>
pl|MMMMdHm|<dn> <Mt> <hN>:<mN>
pl|MMMMdhmz|<dn> <Mt> <hn>:<mN> <at> <zt>
pl|MMMMdHms|<dn> <Mt> <hN>:<mN>:<sN>
pl|MMMMdHmzzzz|<dn> <Mt> <hN>:<mN> <zt>
pl|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> <hn> <at>
pl|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> <hn>:<mN> <at>
pl|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> <hN>:<mN>
pl|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> <hn>:<mN> <at> <zt>
pl|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> <hN>:<mN>:<sN>
pl|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> <hN>:<mN> <zt>
pl|EEEEdh|<Et>, <dn>, <hn> <at>
pl|EEEEdhm|<Et>, <dn>, <hn>:<mN> <at>
pl|EEEEdHm|<Et>, <dn>, <hN>:<mN>
pl|EEEEdhmz|<Et>, <dn>, <hn>:<mN> <at> <zt>
pl|EEEEdHms|<Et>, <dn>, <hN>:<mN>:<sN>
pl|EEEEdHmzzzz|<Et>, <dn>, <hN>:<mN> <zt>
pt|d|<dn>
pt|E|<Et>
pt|Ed|<Et>, <dn>
pt|EEEE|<Et>
pt|EEEEd|<Et>, <dn>
pt|M|<Mn>
pt|Md|<dN>/<MN>
pt|ME|<Mn> <Et>
pt|MEd|<Et>, <dN>/<MN>
pt|MEEEE|<Mn> <Et>
pt|MEEEEd|<Et>, <dN>/<MN>
pt|MM|<MN>
pt|MMd|<dn>/<MN>
pt|MME|<Et> (mês: <MN>)
pt|MMEd|<Et>, <dN>/<MN>
pt|MMEEEE|<MN> <Et>
pt|MMEEEEd|<Et>, <dN>/<MN>
pt|MMM|<Mt>
pt|MMMd|<dn> de <Mt>
pt|MMME|<Mt> <Et>
pt|MMMEd|<Et>, <dn> de <Mt>
pt|MMMEEEE|<Mt> <Et>
pt|MMMEEEEd|<Et>, <dn> de <Mt>
pt|MMMM|<Mt>
pt|MMMMd|<dn> de <Mt>
pt|MMMME|<Et> (mês: <Mt>)
pt|MMMMEd|<Et>, <dn> de <Mt>
pt|MMMMEEEE|<Mt> <Et>
pt|MMMMEEEEd|<Et>, <dn> de <Mt>
pt|y|<yn>
pt|yd|<yn> (dia: <dn>)
pt|yE|<yn> <Et>
pt|yEd|<yn> <Et>, <dn>
pt|yEEEE|<yn> <Et>
pt|yEEEEd|<yn> <Et>, <dn>
pt|yM|<MN>/<yn>
pt|yMd|<dN>/<MN>/<yn>
pt|yME|<MN>/<yn> <Et>
pt|yMEd|<Et>, <dN>/<MN>/<yn>
pt|yMEEEE|<MN>/<yn> <Et>
pt|yMEEEEd|<Et>, <dN>/<MN>/<yn>
pt|yMM|<MN>/<yn>
pt|yMMd|<dn>/<MN>/<yn>
pt|yMME|<MN>/<yn> <Et>
pt|yMMEd|<Et>, <dN>/<MN>/<yn>
pt|yMMEEEE|<MN>/<yn> <Et>
pt|yMMEEEEd|<Et>, <dN>/<MN>/<yn>
pt|yMMM|<Mt> de <yn>
pt|yMMMd|<dn> de <Mt> de <yn>
pt|yMMME|<Mt> de <yn> <Et>
pt|yMMMEd|<Et>, <dn> de <Mt> de <yn>
pt|yMMMEEEE|<Mt> de <yn> <Et>
pt|yMMMEEEEd|<Et>, <dn> de <Mt> de <yn>
pt|yMMMM|<Mt> de <yn>
pt|yMMMMd|<dn> de <Mt> de <yn>
pt|yMMMME|<Mt> de <yn> <Et>
pt|yMMMMEd|<Et>, <dn> de <Mt> de <yn>
pt|yMMMMEEEE|<Mt> de <yn> <Et>
pt|yMMMMEEEEd|<Et>, <dn> de <Mt> de <yn>
pt|Gy|<yn> <Gt>
pt|GyMMMd|<dn> de <Mt> de <yn> <Gt>
pt|GyMMMMEEEEd|<Et>, <dn> de <Mt> de <yn> <Gt>
pt|GyMd|<dN>/<MN>/<yn> <Gt>
pt|s|<sn>
pt|sz|<sn> <zt>
pt|szzzz|<sn> <zt>
pt|m|<mn>
pt|mz|<mn> <zt>
pt|mzzzz|<mn> <zt>
pt|ms|<mN>:<sN>
pt|msz|<mN>:<sN> <zt>
pt|mszzzz|<mN>:<sN> <zt>
pt|h|<hn> <at>
pt|hz|<hn> <at>, <zt>
pt|hzzzz|<hn> <at>, <zt>
pt|hs|<hn> <at> (segundo: <sn>)
pt|hsz|<hn> <at>, <zt> (segundo: <sn>)
pt|hszzzz|<hn> <at>, <zt> (segundo: <sn>)
pt|hm|<hn>:<mN> <at>
pt|hmz|<hn>:<mN> <at> <zt>
pt|hmzzzz|<hn>:<mN> <at> <zt>
pt|hms|<hn>:<mN>:<sN> <at>
pt|hmsz|<hn>:<mN>:<sN> <at> <zt>
pt|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
pt|H|<hN>
pt|Hz|<hN>h, <zt>
pt|Hzzzz|<hN>h, <zt>
pt|Hs|<hN> (segundo: <sn>)
pt|Hsz|<hN>h, <zt> (segundo: <sn>)
pt|Hszzzz|<hN>h, <zt> (segundo: <sn>)
pt|Hm|<hN>:<mN>
pt|Hmz|<hN>:<mN> <zt>
pt|Hmzzzz|<hN>:<mN> <zt>
pt|Hms|<hN>:<mN>:<sN>
pt|Hmsz|<hn>:<mN>:<sN> <zt>
pt|Hmszzzz|<hn>:<mN>:<sN> <zt>
pt|yMMMdh|<dn> de <Mt> de <yn>, <hn> <at>
pt|yMMMdhm|<dn> de <Mt> de <yn>, <hn>:<mN> <at>
pt|yMMMdHm|<dn> de <Mt> de <yn>, <hN>:<mN>
pt|yMMMdhmz|<dn> de <Mt> de <yn>, <hn>:<mN> <at> <zt>
pt|yMMMdHms|<dn> de <Mt> de <yn>, <hN>:<mN>:<sN>
pt|yMMMdHmzzzz|<dn> de <Mt> de <yn>, <hN>:<mN> <zt>
pt|MMMEdh|<Et>, <dn> de <Mt>, <hn> <at>
pt|MMMEdhm|<Et>, <dn> de <Mt>, <hn>:<mN> <at>
pt|MMMEdHm|<Et>, <dn> de <Mt>, <hN>:<mN>
pt|MMMEdhmz|<Et>, <dn> de <Mt>, <hn>:<mN> <at> <zt>
pt|MMMEdHms|<Et>, <dn> de <Mt>, <hN>:<mN>:<sN>
pt|MMMEdHmzzzz|<Et>, <dn> de <Mt>, <hN>:<mN> <zt>
pt|yMdh|<dN>/<MN>/<yn>, <hn> <at>
pt|yMdhm|<dN>/<MN>/<yn>, <hn>:<mN> <at>
pt|yMdHm|<dN>/<MN>/<yn>, <hN>:<mN>
pt|yMdhmz|<dN>/<MN>/<yn>, <hn>:<mN> <at> <zt>
pt|yMdHms|<dN>/<MN>/<yn>, <hN>:<mN>:<sN>
pt|yMdHmzzzz|<dN>/<MN>/<yn>, <hN>:<mN> <zt>
pt|Eh|<Et>, <hn> <at>
pt|Ehm|<Et>, <hn>:<mN> <at>
pt|EHm|<Et>, <hN>:<mN>
pt|Ehmz|<Et>, <hn>:<mN> <at> <zt>
pt|EHms|<Et>, <hN>:<mN>:<sN>
pt|EHmzzzz|<Et>, <hN>:<mN> <zt>
pt|dh|<dn>, <hn> <at>
pt|dhm|<dn>, <hn>:<mN> <at>
pt|dHm|<dn>, <hN>:<mN>
pt|dhmz|<dn>, <hn>:<mN> <at> <zt>
pt|dHms|<dn>, <hN>:<mN>:<sN>
pt|dHmzzzz|<dn>, <hN>:<mN> <zt>
pt|MMMMdh|<dn> de <Mt> às <hn> <at>
pt|MMMMdhm|<dn> de <Mt> às <hn>:<mN> <at>
pt|MMMMdHm|<dn> de <Mt> às <hN>:<mN>
pt|MMMMdhmz|<dn> de <Mt> às <hn>:<mN> <at> <zt>
pt|MMMMdHms|<dn> de <Mt> às <hN>:<mN>:<sN>
pt|MMMMdHmzzzz|<dn> de <Mt> às <hN>:<mN> <zt>
pt|yMMMMEEEEdh|<Et>, <dn> de <Mt> de <yn> às <hn> <at>
pt|yMMMMEEEEdhm|<Et>, <dn> de <Mt> de <yn> às <hn>:<mN> <at>
pt|yMMMMEEEEdHm|<Et>, <dn> de <Mt> de <yn> às <hN>:<mN>
pt|yMMMMEEEEdhmz|<Et>, <dn> de <Mt> de <yn> às <hn>:<mN> <at> <zt>
pt|yMMMMEEEEdHms|<Et>, <dn> de <Mt> de <yn> às <hN>:<mN>:<sN>
pt|yMMMMEEEEdHmzzzz|<Et>, <dn> de <Mt> de <yn> às <hN>:<mN> <zt>
pt|EEEEdh|<Et>, <dn>, <hn> <at>
pt|EEEEdhm|<Et>, <dn>, <hn>:<mN> <at>
pt|EEEEdHm|<Et>, <dn>, <hN>:<mN>
pt|EEEEdhmz|<Et>, <dn>, <hn>:<mN> <at> <zt>
pt|EEEEdHms|<Et>, <dn>, <hN>:<mN>:<sN>
pt|EEEEdHmzzzz|<Et>, <dn>, <hN>:<mN> <zt>
ro|d|<dn>
ro|E|<Et>
ro|Ed|<Et> <dn>
ro|EEEE|<Et>
ro|EEEEd|<Et> <dn>
ro|M|<Mn>
ro|Md|<dN>.<MN>
ro|ME|<Mn> <Et>
ro|MEd|<Et>, <dN>.<MN>
ro|MEEEE|<Mn> <Et>
ro|MEEEEd|<Et>, <dN>.<MN>
ro|MM|<MN>
ro|MMd|<dn>.<MN>
ro|MME|<Et> (lună: <MN>)
ro|MMEd|<Et>, <dN>.<MN>
ro|MMEEEE|<MN> <Et>
ro|MMEEEEd|<Et>, <dN>.<MN>
ro|MMM|<Mt>
ro|MMMd|<dn> <Mt>
ro|MMME|<Mt> <Et>
ro|MMMEd|<Et>, <dn> <Mt>
ro|MMMEEEE|<Mt> <Et>
ro|MMMEEEEd|<Et>, <dn> <Mt>
ro|MMMM|<Mt>
ro|MMMMd|<dn> <Mt>
ro|MMMME|<Et> (lună: <Mt>)
ro|MMMMEd|<Et>, <dn> <Mt>
ro|MMMMEEEE|<Mt> <Et>
ro|MMMMEEEEd|<Et>, <dn> <Mt>
ro|y|<yn>
ro|yd|<yn> (zi: <dn>)
ro|yE|<yn> <Et>
ro|yEd|<yn> <Et> <dn>
ro|yEEEE|<yn> <Et>
ro|yEEEEd|<yn> <Et> <dn>
ro|yM|<MN>.<yn>
ro|yMd|<dN>.<MN>.<yn>
ro|yME|<MN>.<yn> <Et>
ro|yMEd|<Et>, <dN>.<MN>.<yn>
ro|yMEEEE|<MN>.<yn> <Et>
ro|yMEEEEd|<Et>, <dN>.<MN>.<yn>
ro|yMM|<MN>.<yn>
ro|yMMd|<dn>.<MN>.<yn>
ro|yMME|<MN>.<yn> <Et>
ro|yMMEd|<Et>, <dN>.<MN>.<yn>
ro|yMMEEEE|<MN>.<yn> <Et>
ro|yMMEEEEd|<Et>, <dN>.<MN>.<yn>
ro|yMMM|<Mt> <yn>
ro|yMMMd|<dn> <Mt> <yn>
ro|yMMME|<Mt> <yn> <Et>
ro|yMMMEd|<Et>, <dn> <Mt> <yn>
ro|yMMMEEEE|<Mt> <yn> <Et>
ro|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
ro|yMMMM|<Mt> <yn>
ro|yMMMMd|<dn> <Mt> <yn>
ro|yMMMME|<Mt> <yn> <Et>
ro|yMMMMEd|<Et>, <dn> <Mt> <yn>
ro|yMMMMEEEE|<Mt> <yn> <Et>
ro|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
ro|Gy|<yn> <Gt>
ro|GyMMMd|<dn> <Mt> <yn> <Gt>
ro|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
ro|GyMd|<dN>.<MN>.<yn> <Gt>
ro|s|<sn>
ro|sz|<sn> <zt>
ro|szzzz|<sn> <zt>
ro|m|<mn>
ro|mz|<mn> <zt>
ro|mzzzz|<mn> <zt>
ro|ms|<mN>:<sN>
ro|msz|<mN>:<sN> <zt>
ro|mszzzz|<mN>:<sN> <zt>
ro|h|<hn> <at>
ro|hz|<hn> <at> <zt>
ro|hzzzz|<hn> <at> <zt>
ro|hs|<hn> <at> (secundă: <sn>)
ro|hsz|<hn> <at> <zt> (secundă: <sn>)
ro|hszzzz|<hn> <at> <zt> (secundă: <sn>)
ro|hm|<hn>:<mN> <at>
ro|hmz|<hn>:<mN> <at> <zt>
ro|hmzzzz|<hn>:<mN> <at> <zt>
ro|hms|<hn>:<mN>:<sN> <at>
ro|hmsz|<hn>:<mN>:<sN> <at> <zt>
ro|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
ro|H|<hN>
ro|Hz|h <hN> <zt>
ro|Hzzzz|h <hN> <zt>
ro|Hs|<hN> (secundă: <sn>)
ro|Hsz|h <hN> <zt> (secundă: <sn>)
ro|Hszzzz|h <hN> <zt> (secundă: <sn>)
ro|Hm|<hN>:<mN>
ro|Hmz|<hN>:<mN> <zt>
ro|Hmzzzz|<hN>:<mN> <zt>
ro|Hms|<hN>:<mN>:<sN>
ro|Hmsz|<hn>:<mN>:<sN> <zt>
ro|Hmszzzz|<hn>:<mN>:<sN> <zt>
ro|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
ro|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
ro|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
ro|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
ro|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
ro|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
ro|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
ro|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
ro|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
ro|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
ro|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
ro|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
ro|yMdh|<dN>.<MN>.<yn>, <hn> <at>
ro|yMdhm|<dN>.<MN>.<yn>, <hn>:<mN> <at>
ro|yMdHm|<dN>.<MN>.<yn>, <hN>:<mN>
ro|yMdhmz|<dN>.<MN>.<yn>, <hn>:<mN> <at> <zt>
ro|yMdHms|<dN>.<MN>.<yn>, <hN>:<mN>:<sN>
ro|yMdHmzzzz|<dN>.<MN>.<yn>, <hN>:<mN> <zt>
ro|Eh|<Et> <hn> <at>
ro|Ehm|<Et> <hn>:<mN> <at>
ro|EHm|<Et> <hN>:<mN>
ro|Ehmz|<Et>, <hn>:<mN> <at> <zt>
ro|EHms|<Et> <hN>:<mN>:<sN>
ro|EHmzzzz|<Et>, <hN>:<mN> <zt>
ro|dh|<dn>, <hn> <at>
ro|dhm|<dn>, <hn>:<mN> <at>
ro|dHm|<dn>, <hN>:<mN>
ro|dhmz|<dn>, <hn>:<mN> <at> <zt>
ro|dHms|<dn>, <hN>:<mN>:<sN>
ro|dHmzzzz|<dn>, <hN>:<mN> <zt>
ro|MMMMdh|<dn> <Mt> la <hn> <at>
ro|MMMMdhm|<dn> <Mt> la <hn>:<mN> <at>
ro|MMMMdHm|<dn> <Mt> la <hN>:<mN>
ro|MMMMdhmz|<dn> <Mt> la <hn>:<mN> <at> <zt>
ro|MMMMdHms|<dn> <Mt> la <hN>:<mN>:<sN>
ro|MMMMdHmzzzz|<dn> <Mt> la <hN>:<mN> <zt>
ro|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> la <hn> <at>
ro|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> la <hn>:<mN> <at>
ro|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> la <hN>:<mN>
ro|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> la <hn>:<mN> <at> <zt>
ro|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> la <hN>:<mN>:<sN>
ro|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> la <hN>:<mN> <zt>
ro|EEEEdh|<Et> <dn>, <hn> <at>
ro|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
ro|EEEEdHm|<Et> <dn>, <hN>:<mN>
ro|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
ro|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
ro|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
ru|d|<dn>
ru|E|<Et>
ru|Ed|<Et>, <dn>
ru|EEEE|<Et>
ru|EEEEd|<Et>, <dn>
ru|M|<Mn>
ru|Md|<dN>.<MN>
ru|ME|<Mn> <Et>
ru|MEd|<Et>, <dN>.<MN>
ru|MEEEE|<Mn> <Et>
ru|MEEEEd|<Et>, <dN>.<MN>
ru|MM|<MN>
ru|MMd|<dn>.<MN>
ru|MME|<Et> (месяц: <MN>)
ru|MMEd|<Et>, <dN>.<MN>
ru|MMEEEE|<MN> <Et>
ru|MMEEEEd|<Et>, <dN>.<MN>
ru|MMM|<Mt>
ru|MMMd|<dn> <Mt>
ru|MMME|<Mt> <Et>
ru|MMMEd|<Et>, <dn> <Mt>
ru|MMMEEEE|<Mt> <Et>
ru|MMMEEEEd|<Et>, <dn> <Mt>
ru|MMMM|<Mt>
ru|MMMMd|<dn> <Mt>
ru|MMMME|<Et> (месяц: <Mt>)
ru|MMMMEd|<Et>, <dn> <Mt>
ru|MMMMEEEE|<Mt> <Et>
ru|MMMMEEEEd|<Et>, <dn> <Mt>
ru|y|<yn>
ru|yd|<yn> (день: <dn>)
ru|yE|<yn> <Et>
ru|yEd|<yn> <Et>, <dn>
ru|yEEEE|<yn> <Et>
ru|yEEEEd|<yn> <Et>, <dn>
ru|yM|<MN>.<yn>
ru|yMd|<dN>.<MN>.<yn>
ru|yME|<MN>.<yn> <Et>
ru|yMEd|<Et>, <dN>.<MN>.<yn>
ru|yMEEEE|<MN>.<yn> <Et>
ru|yMEEEEd|<Et>, <dN>.<MN>.<yn>
ru|yMM|<MN>.<yn>
ru|yMMd|<dn>.<MN>.<yn>
ru|yMME|<MN>.<yn> <Et>
ru|yMMEd|<Et>, <dN>.<MN>.<yn>
ru|yMMEEEE|<MN>.<yn> <Et>
ru|yMMEEEEd|<Et>, <dN>.<MN>.<yn>
ru|yMMM|<Mt> <yn> г.
ru|yMMMd|<dn> <Mt> <yn> г.
ru|yMMME|<Mt> <yn> г. <Et>
ru|yMMMEd|<Et>, <dn> <Mt> <yn> г.
ru|yMMMEEEE|<Mt> <yn> г. <Et>
ru|yMMMEEEEd|<Et>, <dn> <Mt> <yn> г.
ru|yMMMM|<Mt> <yn> г.
ru|yMMMMd|<dn> <Mt> <yn> г.
ru|yMMMME|<Mt> <yn> г. <Et>
ru|yMMMMEd|<Et>, <dn> <Mt> <yn> г.
ru|yMMMMEEEE|<Mt> <yn> г. <Et>
ru|yMMMMEEEEd|<Et>, <dn> <Mt> <yn> г.
ru|Gy|<yn> г. <Gt>
ru|GyMMMd|<dn> <Mt> <yn> г. <Gt>
ru|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> г. <Gt>
ru|GyMd|<dN>.<MN>.<yn> <Gt>
ru|s|<sn>
ru|sz|<sn> <zt>
ru|szzzz|<sn> <zt>
ru|m|<mn>
ru|mz|<mn> <zt>
ru|mzzzz|<mn> <zt>
ru|ms|<mN>:<sN>
ru|msz|<mN>:<sN> <zt>
ru|mszzzz|<mN>:<sN> <zt>
ru|h|<hn> <at>
ru|hz|<hn> <at>, <zt>
ru|hzzzz|<hn> <at>, <zt>
ru|hs|<hn> <at> (секунда: <sn>)
ru|hsz|<hn> <at>, <zt> (секунда: <sn>)
ru|hszzzz|<hn> <at>, <zt> (секунда: <sn>)
ru|hm|<hn>:<mN> <at>
ru|hmz|<hn>:<mN> <at> <zt>
ru|hmzzzz|<hn>:<mN> <at> <zt>
ru|hms|<hn>:<mN>:<sN> <at>
ru|hmsz|<hn>:<mN>:<sN> <at> <zt>
ru|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
ru|H|<hn>
ru|Hz|<hN> ч. <zt>
ru|Hzzzz|<hN> ч. <zt>
ru|Hs|<hn> (секунда: <sn>)
ru|Hsz|<hN> ч. <zt> (секунда: <sn>)
ru|Hszzzz|<hN> ч. <zt> (секунда: <sn>)
ru|Hm|<hN>:<mN>
ru|Hmz|<hN>:<mN> <zt>
ru|Hmzzzz|<hN>:<mN> <zt>
ru|Hms|<hN>:<mN>:<sN>
ru|Hmsz|<hn>:<mN>:<sN> <zt>
ru|Hmszzzz|<hn>:<mN>:<sN> <zt>
ru|yMMMdh|<dn> <Mt> <yn> г., <hn> <at>
ru|yMMMdhm|<dn> <Mt> <yn> г., <hn>:<mN> <at>
ru|yMMMdHm|<dn> <Mt> <yn> г., <hN>:<mN>
ru|yMMMdhmz|<dn> <Mt> <yn> г., <hn>:<mN> <at> <zt>
ru|yMMMdHms|<dn> <Mt> <yn> г., <hN>:<mN>:<sN>
ru|yMMMdHmzzzz|<dn> <Mt> <yn> г., <hN>:<mN> <zt>
ru|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
ru|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
ru|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
ru|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
ru|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
ru|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
ru|yMdh|<dN>.<MN>.<yn>, <hn> <at>
ru|yMdhm|<dN>.<MN>.<yn>, <hn>:<mN> <at>
ru|yMdHm|<dN>.<MN>.<yn>, <hN>:<mN>
ru|yMdhmz|<dN>.<MN>.<yn>, <hn>:<mN> <at> <zt>
ru|yMdHms|<dN>.<MN>.<yn>, <hN>:<mN>:<sN>
ru|yMdHmzzzz|<dN>.<MN>.<yn>, <hN>:<mN> <zt>
ru|Eh|<Et>, <hn> <at>
ru|Ehm|<Et> <hn>:<mN> <at>
ru|EHm|<Et> <hN>:<mN>
ru|Ehmz|<Et>, <hn>:<mN> <at> <zt>
ru|EHms|<Et> <hN>:<mN>:<sN>
ru|EHmzzzz|<Et>, <hN>:<mN> <zt>
ru|dh|<dn>, <hn> <at>
ru|dhm|<dn>, <hn>:<mN> <at>
ru|dHm|<dn>, <hN>:<mN>
ru|dhmz|<dn>, <hn>:<mN> <at> <zt>
ru|dHms|<dn>, <hN>:<mN>:<sN>
ru|dHmzzzz|<dn>, <hN>:<mN> <zt>
ru|MMMMdh|<dn> <Mt> в <hn> <at>
ru|MMMMdhm|<dn> <Mt> в <hn>:<mN> <at>
ru|MMMMdHm|<dn> <Mt> в <hN>:<mN>
ru|MMMMdhmz|<dn> <Mt> в <hn>:<mN> <at> <zt>
ru|MMMMdHms|<dn> <Mt> в <hN>:<mN>:<sN>
ru|MMMMdHmzzzz|<dn> <Mt> в <hN>:<mN> <zt>
ru|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> г. в <hn> <at>
ru|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> г. в <hn>:<mN> <at>
ru|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> г. в <hN>:<mN>
ru|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> г. в <hn>:<mN> <at> <zt>
ru|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> г. в <hN>:<mN>:<sN>
ru|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> г. в <hN>:<mN> <zt>
ru|EEEEdh|<Et>, <dn>, <hn> <at>
ru|EEEEdhm|<Et>, <dn>, <hn>:<mN> <at>
ru|EEEEdHm|<Et>, <dn>, <hN>:<mN>
ru|EEEEdhmz|<Et>, <dn>, <hn>:<mN> <at> <zt>
ru|EEEEdHms|<Et>, <dn>, <hN>:<mN>:<sN>
ru|EEEEdHmzzzz|<Et>, <dn>, <hN>:<mN> <zt>
sk|d|<dn>.
sk|E|<Et>
sk|Ed|<Et> <dn>.
sk|EEEE|<Et>
sk|EEEEd|<Et> <dn>.
sk|M|<Mn>.
sk|Md|<dn>. <Mn>.
sk|ME|<Mn>. <Et>
sk|MEd|<Et> <dn>. <Mn>.
sk|MEEEE|<Mn>. <Et>
sk|MEEEEd|<Et> <dn>. <Mn>.
sk|MM|<MN>.
sk|MMd|<dn>. <MN>.
sk|MME|<Et> (mesiac: <MN>.)
sk|MMEd|<Et> <dn>. <MN>.
sk|MMEEEE|<MN>. <Et>
sk|MMEEEEd|<Et> <dn>. <MN>.
sk|MMM|<Mt>
sk|MMMd|<dn>. <Mn>.
sk|MMME|<Mt> <Et>
sk|MMMEd|<Et> <dn>. <Mn>.
sk|MMMEEEE|<Mt> <Et>
sk|MMMEEEEd|<Et> <dn>. <Mn>.
sk|MMMM|<Mt>
sk|MMMMd|<dn>. <Mt>
sk|MMMME|<Et> (mesiac: <Mt>)
sk|MMMMEd|<Et> <dn>. <Mt>
sk|MMMMEEEE|<Mt> <Et>
sk|MMMMEEEEd|<Et> <dn>. <Mt>
sk|y|<yn>
sk|yd|<yn> (deň: <dn>.)
sk|yE|<yn> <Et>
sk|yEd|<yn> <Et> <dn>.
sk|yEEEE|<yn> <Et>
sk|yEEEEd|<yn> <Et> <dn>.
sk|yM|<Mn>/<yn>
sk|yMd|<dn>. <Mn>. <yn>
sk|yME|<Mn>/<yn> <Et>
sk|yMEd|<Et> <dn>. <Mn>. <yn>
sk|yMEEEE|<Mn>/<yn> <Et>
sk|yMEEEEd|<Et> <dn>. <Mn>. <yn>
sk|yMM|<MN>/<yn>
sk|yMMd|<dn>. <MN>. <yn>
sk|yMME|<MN>/<yn> <Et>
sk|yMMEd|<Et> <dn>. <MN>. <yn>
sk|yMMEEEE|<MN>/<yn> <Et>
sk|yMMEEEEd|<Et> <dn>. <MN>. <yn>
sk|yMMM|<Mn>/<yn>
sk|yMMMd|<dn>. <Mn>. <yn>
sk|yMMME|<Mn>/<yn> <Et>
sk|yMMMEd|<Et> <dn>. <Mn>. <yn>
sk|yMMMEEEE|<Mn>/<yn> <Et>
sk|yMMMEEEEd|<Et> <dn>. <Mt> <yn>
sk|yMMMM|<Mt> <yn>
sk|yMMMMd|<dn>. <Mt> <yn>
sk|yMMMME|<Mt> <yn> <Et>
sk|yMMMMEd|<Et> <dn>. <Mt> <yn>
sk|yMMMMEEEE|<Mt> <yn> <Et>
sk|yMMMMEEEEd|<Et> <dn>. <Mt> <yn>
sk|Gy|<yn> <Gt>
sk|GyMMMd|<dn>. <Mn>. <yn> <Gt>
sk|GyMMMMEEEEd|<Et> <dn>. <Mn>. <yn> <Gt>
sk|GyMd|<dn>. <Mn>. <yn> <Gt>
sk|s|<sn>
sk|sz|<sn> <zt>
sk|szzzz|<sn> <zt>
sk|m|<mn>
sk|mz|<mn> <zt>
sk|mzzzz|<mn> <zt>
sk|ms|<mN>:<sN>
sk|msz|<mN>:<sN> <zt>
sk|mszzzz|<mN>:<sN> <zt>
sk|h|<hn> <at>
sk|hz|<hn> <at> <zt>
sk|hzzzz|<hn> <at> <zt>
sk|hs|<hn> <at> (sekunda: <sn>)
sk|hsz|<hn> <at> <zt> (sekunda: <sn>)
sk|hszzzz|<hn> <at> <zt> (sekunda: <sn>)
sk|hm|<hn>:<mN> <at>
sk|hmz|<hn>:<mN> <at> <zt>
sk|hmzzzz|<hn>:<mN> <at> <zt>
sk|hms|<hn>:<mN>:<sN> <at>
sk|hmsz|<hn>:<mN>:<sN> <at> <zt>
sk|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
sk|H|<hn>
sk|Hz|<hN>h <zt>
sk|Hzzzz|<hN>h <zt>
sk|Hs|<hn> (sekunda: <sn>)
sk|Hsz|<hN>h <zt> (sekunda: <sn>)
sk|Hszzzz|<hN>h <zt> (sekunda: <sn>)
sk|Hm|<hn>:<mN>
sk|Hmz|<hn>:<mN> <zt>
sk|Hmzzzz|<hn>:<mN> <zt>
sk|Hms|<hn>:<mN>:<sN>
sk|Hmsz|<hn>:<mN>:<sN> <zt>
sk|Hmszzzz|<hn>:<mN>:<sN> <zt>
sk|yMMMdh|<dn>. <Mn>. <yn>, <hn> <at>
sk|yMMMdhm|<dn>. <Mn>. <yn>, <hn>:<mN> <at>
sk|yMMMdHm|<dn>. <Mn>. <yn>, <hn>:<mN>
sk|yMMMdhmz|<dn>. <Mn>. <yn>, <hn>:<mN> <at> <zt>
sk|yMMMdHms|<dn>. <Mn>. <yn>, <hn>:<mN>:<sN>
sk|yMMMdHmzzzz|<dn>. <Mn>. <yn>, <hn>:<mN> <zt>
sk|MMMEdh|<Et> <dn>. <Mn>., <hn> <at>
sk|MMMEdhm|<Et> <dn>. <Mn>., <hn>:<mN> <at>
sk|MMMEdHm|<Et> <dn>. <Mn>., <hn>:<mN>
sk|MMMEdhmz|<Et> <dn>. <Mn>., <hn>:<mN> <at> <zt>
sk|MMMEdHms|<Et> <dn>. <Mn>., <hn>:<mN>:<sN>
sk|MMMEdHmzzzz|<Et> <dn>. <Mn>., <hn>:<mN> <zt>
sk|yMdh|<dn>. <Mn>. <yn> <hn> <at>
sk|yMdhm|<dn>. <Mn>. <yn> <hn>:<mN> <at>
sk|yMdHm|<dn>. <Mn>. <yn> <hn>:<mN>
sk|yMdhmz|<dn>. <Mn>. <yn> <hn>:<mN> <at> <zt>
sk|yMdHms|<dn>. <Mn>. <yn> <hn>:<mN>:<sN>
sk|yMdHmzzzz|<dn>. <Mn>. <yn> <hn>:<mN> <zt>
sk|Eh|<Et> <hn> <at>
sk|Ehm|<Et> <hn>:<mN> <at>
sk|EHm|<Et> <hN>:<mN>
sk|Ehmz|<Et> <hn>:<mN> <at> <zt>
sk|EHms|<Et> <hN>:<mN>:<sN>
sk|EHmzzzz|<Et> <hn>:<mN> <zt>
sk|dh|<dn>. <hn> <at>
sk|dhm|<dn>. <hn>:<mN> <at>
sk|dHm|<dn>. <hn>:<mN>
sk|dhmz|<dn>. <hn>:<mN> <at> <zt>
sk|dHms|<dn>. <hn>:<mN>:<sN>
sk|dHmzzzz|<dn>. <hn>:<mN> <zt>
sk|MMMMdh|<dn>. <Mt> o <hn> <at>
sk|MMMMdhm|<dn>. <Mt> o <hn>:<mN> <at>
sk|MMMMdHm|<dn>. <Mt> o <hn>:<mN>
sk|MMMMdhmz|<dn>. <Mt> o <hn>:<mN> <at> <zt>
sk|MMMMdHms|<dn>. <Mt> o <hn>:<mN>:<sN>
sk|MMMMdHmzzzz|<dn>. <Mt> o <hn>:<mN> <zt>
sk|yMMMMEEEEdh|<Et> <dn>. <Mt> <yn> o <hn> <at>
sk|yMMMMEEEEdhm|<Et> <dn>. <Mt> <yn> o <hn>:<mN> <at>
sk|yMMMMEEEEdHm|<Et> <dn>. <Mt> <yn> o <hn>:<mN>
sk|yMMMMEEEEdhmz|<Et> <dn>. <Mt> <yn> o <hn>:<mN> <at> <zt>
sk|yMMMMEEEEdHms|<Et> <dn>. <Mt> <yn> o <hn>:<mN>:<sN>
sk|yMMMMEEEEdHmzzzz|<Et> <dn>. <Mt> <yn> o <hn>:<mN> <zt>
sk|EEEEdh|<Et> <dn>. <hn> <at>
sk|EEEEdhm|<Et> <dn>. <hn>:<mN> <at>
sk|EEEEdHm|<Et> <dn>. <hn>:<mN>
sk|EEEEdhmz|<Et> <dn>. <hn>:<mN> <at> <zt>
sk|EEEEdHms|<Et> <dn>. <hn>:<mN>:<sN>
sk|EEEEdHmzzzz|<Et> <dn>. <hn>:<mN> <zt>
sl|d|<dn>.
sl|E|<Et>
sl|Ed|<Et>, <dn>.
sl|EEEE|<Et>
sl|EEEEd|<Et>, <dn>.
sl|M|<Mn>
sl|Md|<dn>. <Mn>.
sl|ME|<Mn> <Et>
sl|MEd|<Et>, <dn>. <Mn>.
sl|MEEEE|<Mn> <Et>
sl|MEEEEd|<Et>, <dn>. <Mn>.
sl|MM|<MN>
sl|MMd|<dn>. <MN>.
sl|MME|<Et> (mesec: <MN>)
sl|MMEd|<Et>, <dn>. <MN>.
sl|MMEEEE|<MN> <Et>
sl|MMEEEEd|<Et>, <dn>. <MN>.
sl|MMM|<Mt>
sl|MMMd|<dn>. <Mt>
sl|MMME|<Mt> <Et>
sl|MMMEd|<Et>, <dn>. <Mt>
sl|MMMEEEE|<Mt> <Et>
sl|MMMEEEEd|<Et>, <dn>. <Mt>
sl|MMMM|<Mt>
sl|MMMMd|<dn>. <Mt>
sl|MMMME|<Et> (mesec: <Mt>)
sl|MMMMEd|<Et>, <dn>. <Mt>
sl|MMMMEEEE|<Mt> <Et>
sl|MMMMEEEEd|<Et>, <dn>. <Mt>
sl|y|<yn>
sl|yd|<yn> (dan: <dn>.)
sl|yE|<yn> <Et>
sl|yEd|<yn> <Et>, <dn>.
sl|yEEEE|<yn> <Et>
sl|yEEEEd|<yn> <Et>, <dn>.
sl|yM|<Mn>/<yn>
sl|yMd|<dn>. <Mn>. <yn>
sl|yME|<Mn>/<yn> <Et>
sl|yMEd|<Et>, <dn>. <Mn>. <yn>
sl|yMEEEE|<Mn>/<yn> <Et>
sl|yMEEEEd|<Et>, <dn>. <Mn>. <yn>
sl|yMM|<MN>/<yn>
sl|yMMd|<dn>. <MN>. <yn>
sl|yMME|<MN>/<yn> <Et>
sl|yMMEd|<Et>, <dn>. <MN>. <yn>
sl|yMMEEEE|<MN>/<yn> <Et>
sl|yMMEEEEd|<Et>, <dn>. <MN>. <yn>
sl|yMMM|<Mt> <yn>
sl|yMMMd|<dn>. <Mt> <yn>
sl|yMMME|<Mt> <yn> <Et>
sl|yMMMEd|<Et>, <dn>. <Mt> <yn>
sl|yMMMEEEE|<Mt> <yn> <Et>
sl|yMMMEEEEd|<Et>, <dn>. <Mt> <yn>
sl|yMMMM|<Mt> <yn>
sl|yMMMMd|<dn>. <Mt> <yn>
sl|yMMMME|<Mt> <yn> <Et>
sl|yMMMMEd|<Et>, <dn>. <Mt> <yn>
sl|yMMMMEEEE|<Mt> <yn> <Et>
sl|yMMMMEEEEd|<Et>, <dn>. <Mt> <yn>
sl|Gy|<yn> <Gt>
sl|GyMMMd|<dn>. <Mt> <yn> <Gt>
sl|GyMMMMEEEEd|<Et>, <dn>. <Mt> <yn> <Gt>
sl|GyMd|<dn>. <Mn>. <yn> <Gt>
sl|s|<sn>
sl|sz|<sn> <zt>
sl|szzzz|<sn> <zt>
sl|m|<mn>
sl|mz|<mn> <zt>
sl|mzzzz|<mn> <zt>
sl|ms|<mN>:<sN>
sl|msz|<mN>:<sN> <zt>
sl|mszzzz|<mN>:<sN> <zt>
sl|h|<hn> <at>
sl|hz|<hn> <at> <zt>
sl|hzzzz|<hn> <at> <zt>
sl|hs|<hn> <at> (sekunda: <sn>)
sl|hsz|<hn> <at> <zt> (sekunda: <sn>)
sl|hszzzz|<hn> <at> <zt> (sekunda: <sn>)
sl|hm|<hn>:<mN> <at>
sl|hmz|<hn>:<mN> <at> <zt>
sl|hmzzzz|<hn>:<mN> <at> <zt>
sl|hms|<hn>:<mN>:<sN> <at>
sl|hmsz|<hn>:<mN>:<sN> <at> <zt>
sl|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
sl|H|<hN>
sl|Hz|<hN>h <zt>
sl|Hzzzz|<hN>h <zt>
sl|Hs|<hN> (sekunda: <sn>)
sl|Hsz|<hN>h <zt> (sekunda: <sn>)
sl|Hszzzz|<hN>h <zt> (sekunda: <sn>)
sl|Hm|<hN>:<mN>
sl|Hmz|<hN>:<mN> <zt>
sl|Hmzzzz|<hN>:<mN> <zt>
sl|Hms|<hN>:<mN>:<sN>
sl|Hmsz|<hn>:<mN>:<sN> <zt>
sl|Hmszzzz|<hn>:<mN>:<sN> <zt>
sl|yMMMdh|<dn>. <Mt> <yn>, <hn> <at>
sl|yMMMdhm|<dn>. <Mt> <yn>, <hn>:<mN> <at>
sl|yMMMdHm|<dn>. <Mt> <yn>, <hN>:<mN>
sl|yMMMdhmz|<dn>. <Mt> <yn>, <hn>:<mN> <at> <zt>
sl|yMMMdHms|<dn>. <Mt> <yn>, <hN>:<mN>:<sN>
sl|yMMMdHmzzzz|<dn>. <Mt> <yn>, <hN>:<mN> <zt>
sl|MMMEdh|<Et>, <dn>. <Mt>, <hn> <at>
sl|MMMEdhm|<Et>, <dn>. <Mt>, <hn>:<mN> <at>
sl|MMMEdHm|<Et>, <dn>. <Mt>, <hN>:<mN>
sl|MMMEdhmz|<Et>, <dn>. <Mt>, <hn>:<mN> <at> <zt>
sl|MMMEdHms|<Et>, <dn>. <Mt>, <hN>:<mN>:<sN>
sl|MMMEdHmzzzz|<Et>, <dn>. <Mt>, <hN>:<mN> <zt>
sl|yMdh|<dn>. <Mn>. <yn>, <hn> <at>
sl|yMdhm|<dn>. <Mn>. <yn>, <hn>:<mN> <at>
sl|yMdHm|<dn>. <Mn>. <yn>, <hN>:<mN>
sl|yMdhmz|<dn>. <Mn>. <yn>, <hn>:<mN> <at> <zt>
sl|yMdHms|<dn>. <Mn>. <yn>, <hN>:<mN>:<sN>
sl|yMdHmzzzz|<dn>. <Mn>. <yn>, <hN>:<mN> <zt>
sl|Eh|<Et>, <hn> <at>
sl|Ehm|<Et>, <hn>:<mN> <at>
sl|EHm|<Et>, <hN>:<mN>
sl|Ehmz|<Et>, <hn>:<mN> <at> <zt>
sl|EHms|<Et>, <hN>:<mN>:<sN>
sl|EHmzzzz|<Et>, <hN>:<mN> <zt>
sl|dh|<dn>., <hn> <at>
sl|dhm|<dn>., <hn>:<mN> <at>
sl|dHm|<dn>., <hN>:<mN>
sl|dhmz|<dn>., <hn>:<mN> <at> <zt>
sl|dHms|<dn>., <hN>:<mN>:<sN>
sl|dHmzzzz|<dn>., <hN>:<mN> <zt>
sl|MMMMdh|<dn>. <Mt> ob <hn> <at>
sl|MMMMdhm|<dn>. <Mt> ob <hn>:<mN> <at>
sl|MMMMdHm|<dn>. <Mt> ob <hN>:<mN>
sl|MMMMdhmz|<dn>. <Mt> ob <hn>:<mN> <at> <zt>
sl|MMMMdHms|<dn>. <Mt> ob <hN>:<mN>:<sN>
sl|MMMMdHmzzzz|<dn>. <Mt> ob <hN>:<mN> <zt>
sl|yMMMMEEEEdh|<Et>, <dn>. <Mt> <yn> ob <hn> <at>
sl|yMMMMEEEEdhm|<Et>, <dn>. <Mt> <yn> ob <hn>:<mN> <at>
sl|yMMMMEEEEdHm|<Et>, <dn>. <Mt> <yn> ob <hN>:<mN>
sl|yMMMMEEEEdhmz|<Et>, <dn>. <Mt> <yn> ob <hn>:<mN> <at> <zt>
sl|yMMMMEEEEdHms|<Et>, <dn>. <Mt> <yn> ob <hN>:<mN>:<sN>
sl|yMMMMEEEEdHmzzzz|<Et>, <dn>. <Mt> <yn> ob <hN>:<mN> <zt>
sl|EEEEdh|<Et>, <dn>., <hn> <at>
sl|EEEEdhm|<Et>, <dn>., <hn>:<mN> <at>
sl|EEEEdHm|<Et>, <dn>., <hN>:<mN>
sl|EEEEdhmz|<Et>, <dn>., <hn>:<mN> <at> <zt>
sl|EEEEdHms|<Et>, <dn>., <hN>:<mN>:<sN>
sl|EEEEdHmzzzz|<Et>, <dn>., <hN>:<mN> <zt>
sq|d|<dn>
sq|E|<Et>
sq|Ed|<Et>, <dn>
sq|EEEE|<Et>
sq|EEEEd|<Et>, <dn>
sq|M|<Mn>
sq|Md|<dn>.<Mn>
sq|ME|<Mn> <Et>
sq|MEd|<Et>, <dn>.<Mn>
sq|MEEEE|<Mn> <Et>
sq|MEEEEd|<Et>, <dn>.<Mn>
sq|MM|<MN>
sq|MMd|<dn>.<Mn>
sq|MME|<Et> (muaj: <MN>)
sq|MMEd|<Et>, <dn>.<MN>
sq|MMEEEE|<MN> <Et>
sq|MMEEEEd|<Et>, <dn>.<MN>
sq|MMM|<Mt>
sq|MMMd|<dn> <Mt>
sq|MMME|<Mt> <Et>
sq|MMMEd|<Et>, <dn> <Mt>
sq|MMMEEEE|<Mt> <Et>
sq|MMMEEEEd|<Et>, <dn> <Mt>
sq|MMMM|<Mt>
sq|MMMMd|<dn> <Mt>
sq|MMMME|<Et> (muaj: <Mt>)
sq|MMMMEd|<Et>, <dn> <Mt>
sq|MMMMEEEE|<Mt> <Et>
sq|MMMMEEEEd|<Et>, <dn> <Mt>
sq|y|<yn>
sq|yd|<yn> (ditë: <dn>)
sq|yE|<yn> <Et>
sq|yEd|<yn> <Et>, <dn>
sq|yEEEE|<yn> <Et>
sq|yEEEEd|<yn> <Et>, <dn>
sq|yM|<Mn>.<yn>
sq|yMd|<dn>.<Mn>.<yn>
sq|yME|<Mn>.<yn> <Et>
sq|yMEd|<Et>, <dn>.<Mn>.<yn>
sq|yMEEEE|<Mn>.<yn> <Et>
sq|yMEEEEd|<Et>, <dn>.<Mn>.<yn>
sq|yMM|<MN>.<yn>
sq|yMMd|<dn>.<MN>.<yn>
sq|yMME|<MN>.<yn> <Et>
sq|yMMEd|<Et>, <dn>.<MN>.<yn>
sq|yMMEEEE|<MN>.<yn> <Et>
sq|yMMEEEEd|<Et>, <dn>.<MN>.<yn>
sq|yMMM|<Mt> <yn>
sq|yMMMd|<dn> <Mt> <yn>
sq|yMMME|<Mt> <yn> <Et>
sq|yMMMEd|<Et>, <dn> <Mt> <yn>
sq|yMMMEEEE|<Mt> <yn> <Et>
sq|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
sq|yMMMM|<Mt> <yn>
sq|yMMMMd|<dn> <Mt> <yn>
sq|yMMMME|<Mt> <yn> <Et>
sq|yMMMMEd|<Et>, <dn> <Mt> <yn>
sq|yMMMMEEEE|<Mt> <yn> <Et>
sq|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
sq|Gy|<yn> <Gt>
sq|GyMMMd|<dn> <Mt> <yn> <Gt>
sq|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
sq|GyMd|<dn>.<Mn>.<yn> <Gt>
sq|s|<sn>
sq|sz|<sn>, <zt>
sq|szzzz|<sn>, <zt>
sq|m|<mn>
sq|mz|<mn>, <zt>
sq|mzzzz|<mn>, <zt>
sq|ms|<mN>:<sN>
sq|msz|<mN>:<sN>, <zt>
sq|mszzzz|<mN>:<sN>, <zt>
sq|h|<hn> <at>
sq|hz|<hn> <at>, <zt>
sq|hzzzz|<hn> <at>, <zt>
sq|hs|<hn> <at> (sekondë: <sn>)
sq|hsz|<hn> <at>, <zt> (sekondë: <sn>)
sq|hszzzz|<hn> <at>, <zt> (sekondë: <sn>)
sq|hm|<hn>:<mN> <at>
sq|hmz|<hn>:<mN> <at>, <zt>
sq|hmzzzz|<hn>:<mN> <at>, <zt>
sq|hms|<hn>:<mN>:<sN> <at>
sq|hmsz|<hn>:<mN>:<sN> <at>, <zt>
sq|hmszzzz|<hn>:<mN>:<sN> <at>, <zt>
sq|H|<hN>
sq|Hz|<hN>, <zt>
sq|Hzzzz|<hN>, <zt>
sq|Hs|<hN> (sekondë: <sn>)
sq|Hsz|<hN>, <zt> (sekondë: <sn>)
sq|Hszzzz|<hN>, <zt> (sekondë: <sn>)
sq|Hm|<hN>:<mN>
sq|Hmz|<hN>:<mN>, <zt>
sq|Hmzzzz|<hN>:<mN>, <zt>
sq|Hms|<hN>:<mN>:<sN>
sq|Hmsz|<hN>:<mN>:<sN>, <zt>
sq|Hmszzzz|<hN>:<mN>:<sN>, <zt>
sq|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
sq|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
sq|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
sq|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at>, <zt>
sq|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
sq|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN>, <zt>
sq|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
sq|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
sq|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
sq|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at>, <zt>
sq|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
sq|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN>, <zt>
sq|yMdh|<dn>.<Mn>.<yn>, <hn> <at>
sq|yMdhm|<dn>.<Mn>.<yn>, <hn>:<mN> <at>
sq|yMdHm|<dn>.<Mn>.<yn>, <hN>:<mN>
sq|yMdhmz|<dn>.<Mn>.<yn>, <hn>:<mN> <at>, <zt>
sq|yMdHms|<dn>.<Mn>.<yn>, <hN>:<mN>:<sN>
sq|yMdHmzzzz|<dn>.<Mn>.<yn>, <hN>:<mN>, <zt>
sq|Eh|<Et>, <hn> <at>
sq|Ehm|<Et>, <hn>:<mN> <at>
sq|EHm|<Et>, <hN>:<mN>
sq|Ehmz|<Et>, <hn>:<mN> <at>, <zt>
sq|EHms|<Et>, <hN>:<mN>:<sN>
sq|EHmzzzz|<Et>, <hN>:<mN>, <zt>
sq|dh|<dn>, <hn> <at>
sq|dhm|<dn>, <hn>:<mN> <at>
sq|dHm|<dn>, <hN>:<mN>
sq|dhmz|<dn>, <hn>:<mN> <at>, <zt>
sq|dHms|<dn>, <hN>:<mN>:<sN>
sq|dHmzzzz|<dn>, <hN>:<mN>, <zt>
sq|MMMMdh|<dn> <Mt> në <hn> <at>
sq|MMMMdhm|<dn> <Mt> në <hn>:<mN> <at>
sq|MMMMdHm|<dn> <Mt> në <hN>:<mN>
sq|MMMMdhmz|<dn> <Mt> në <hn>:<mN> <at>, <zt>
sq|MMMMdHms|<dn> <Mt> në <hN>:<mN>:<sN>
sq|MMMMdHmzzzz|<dn> <Mt> në <hN>:<mN>, <zt>
sq|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> në <hn> <at>
sq|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> në <hn>:<mN> <at>
sq|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> në <hN>:<mN>
sq|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> në <hn>:<mN> <at>, <zt>
sq|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> në <hN>:<mN>:<sN>
sq|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> në <hN>:<mN>, <zt>
sq|EEEEdh|<Et>, <dn>, <hn> <at>
sq|EEEEdhm|<Et>, <dn>, <hn>:<mN> <at>
sq|EEEEdHm|<Et>, <dn>, <hN>:<mN>
sq|EEEEdhmz|<Et>, <dn>, <hn>:<mN> <at>, <zt>
sq|EEEEdHms|<Et>, <dn>, <hN>:<mN>:<sN>
sq|EEEEdHmzzzz|<Et>, <dn>, <hN>:<mN>, <zt>
sr|d|<dn>
sr|E|<Et>
sr|Ed|<Et> <dn>.
sr|EEEE|<Et>
sr|EEEEd|<Et> <dn>.
sr|M|<Mn>
sr|Md|<dn>. <Mn>.
sr|ME|<Mn> <Et>
sr|MEd|<Et>, <dn>. <Mn>.
sr|MEEEE|<Mn> <Et>
sr|MEEEEd|<Et>, <dn>. <Mn>.
sr|MM|<MN>
sr|MMd|<dn>.<MN>.
sr|MME|<Et> (месец: <MN>)
sr|MMEd|<Et>, <dn>. <MN>.
sr|MMEEEE|<MN> <Et>
sr|MMEEEEd|<Et>, <dn>. <MN>.
sr|MMM|<Mt>
sr|MMMd|<dn>. <Mt>
sr|MMME|<Mt> <Et>
sr|MMMEd|<Et> <dn>. <Mt>
sr|MMMEEEE|<Mt> <Et>
sr|MMMEEEEd|<Et> <dn>. <Mt>
sr|MMMM|<Mt>
sr|MMMMd|<dn>. <Mt>
sr|MMMME|<Et> (месец: <Mt>)
sr|MMMMEd|<Et>, <dn>. <Mt>
sr|MMMMEEEE|<Mt> <Et>
sr|MMMMEEEEd|<Et>, <dn>. <Mt>
sr|y|<yn>.
sr|yd|<yn>. (дан: <dn>)
sr|yE|<yn>. <Et>
sr|yEd|<yn>. <Et> <dn>.
sr|yEEEE|<yn>. <Et>
sr|yEEEEd|<yn>. <Et> <dn>.
sr|yM|<Mn>. <yn>.
sr|yMd|<dn>. <Mn>. <yn>.
sr|yME|<Mn>. <yn>. <Et>
sr|yMEd|<Et>, <dn>. <Mn>. <yn>.
sr|yMEEEE|<Mn>. <yn>. <Et>
sr|yMEEEEd|<Et>, <dn>. <Mn>. <yn>.
sr|yMM|<MN>.<yn>.
sr|yMMd|<dn>. <MN>. <yn>.
sr|yMME|<MN>.<yn>. <Et>
sr|yMMEd|<Et>, <dn>. <MN>. <yn>.
sr|yMMEEEE|<MN>.<yn>. <Et>
sr|yMMEEEEd|<Et>, <dn>. <MN>. <yn>.
sr|yMMM|<Mt> <yn>.
sr|yMMMd|<dn>. <Mt> <yn>.
sr|yMMME|<Mt> <yn>. <Et>
sr|yMMMEd|<Et>, <dn>. <Mt> <yn>.
sr|yMMMEEEE|<Mt> <yn>. <Et>
sr|yMMMEEEEd|<Et>, <dn>. <Mt> <yn>.
sr|yMMMM|<Mt> <yn>.
sr|yMMMMd|<dn>. <Mt> <yn>.
sr|yMMMME|<Mt> <yn>. <Et>
sr|yMMMMEd|<Et>, <dn>. <Mt> <yn>.
sr|yMMMMEEEE|<Mt> <yn>. <Et>
sr|yMMMMEEEEd|<Et>, <dn>. <Mt> <yn>.
sr|Gy|<yn>. <Gt>
sr|GyMMMd|<dn>. <Mt> <yn>. <Gt>
sr|GyMMMMEEEEd|<Et>, <dn>. <Mt> <yn>. <Gt>
sr|GyMd|<dn>.<MN>.<yn>. <Gt>
sr|s|<sn>
sr|sz|<sn> <zt>
sr|szzzz|<sn> <zt>
sr|m|<mn>
sr|mz|<mn> <zt>
sr|mzzzz|<mn> <zt>
sr|ms|<mN>:<sN>
sr|msz|<mN>:<sN> <zt>
sr|mszzzz|<mN>:<sN> <zt>
sr|h|<hn> <at>
sr|hz|<hn> <at> <zt>
sr|hzzzz|<hn> <at> <zt>
sr|hs|<hn> <at> (секунд: <sn>)
sr|hsz|<hn> <at> <zt> (секунд: <sn>)
sr|hszzzz|<hn> <at> <zt> (секунд: <sn>)
sr|hm|<hn>:<mN> <at>
sr|hmz|<hn>:<mN> <at> <zt>
sr|hmzzzz|<hn>:<mN> <at> <zt>
sr|hms|<hn>:<mN>:<sN> <at>
sr|hmsz|<hn>:<mN>:<sN> <at> <zt>
sr|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
sr|H|<hN>
sr|Hz|<hN> h <zt>
sr|Hzzzz|<hN> h <zt>
sr|Hs|<hN> (секунд: <sn>)
sr|Hsz|<hN> h <zt> (секунд: <sn>)
sr|Hszzzz|<hN> h <zt> (секунд: <sn>)
sr|Hm|<hN>:<mN>
sr|Hmz|<hN>:<mN> <zt>
sr|Hmzzzz|<hN>:<mN> <zt>
sr|Hms|<hN>:<mN>:<sN>
sr|Hmsz|<hn>:<mN>:<sN> <zt>
sr|Hmszzzz|<hn>:<mN>:<sN> <zt>
sr|yMMMdh|<dn>. <Mt> <yn>. <hn> <at>
sr|yMMMdhm|<dn>. <Mt> <yn>. <hn>:<mN> <at>
sr|yMMMdHm|<dn>. <Mt> <yn>. <hN>:<mN>
sr|yMMMdhmz|<dn>. <Mt> <yn>. <hn>:<mN> <at> <zt>
sr|yMMMdHms|<dn>. <Mt> <yn>. <hN>:<mN>:<sN>
sr|yMMMdHmzzzz|<dn>. <Mt> <yn>. <hN>:<mN> <zt>
sr|MMMEdh|<Et> <dn>. <Mt> <hn> <at>
sr|MMMEdhm|<Et> <dn>. <Mt> <hn>:<mN> <at>
sr|MMMEdHm|<Et> <dn>. <Mt> <hN>:<mN>
sr|MMMEdhmz|<Et> <dn>. <Mt> <hn>:<mN> <at> <zt>
sr|MMMEdHms|<Et> <dn>. <Mt> <hN>:<mN>:<sN>
sr|MMMEdHmzzzz|<Et> <dn>. <Mt> <hN>:<mN> <zt>
sr|yMdh|<dn>. <Mn>. <yn>. <hn> <at>
sr|yMdhm|<dn>. <Mn>. <yn>. <hn>:<mN> <at>
sr|yMdHm|<dn>. <Mn>. <yn>. <hN>:<mN>
sr|yMdhmz|<dn>. <Mn>. <yn>. <hn>:<mN> <at> <zt>
sr|yMdHms|<dn>. <Mn>. <yn>. <hN>:<mN>:<sN>
sr|yMdHmzzzz|<dn>. <Mn>. <yn>. <hN>:<mN> <zt>
sr|Eh|<Et> <hn> <at>
sr|Ehm|<Et> <hn>:<mN> <at>
sr|EHm|<Et> <hN>:<mN>
sr|Ehmz|<Et> <hn>:<mN> <at> <zt>
sr|EHms|<Et> <hN>:<mN>:<sN>
sr|EHmzzzz|<Et> <hN>:<mN> <zt>
sr|dh|<dn> <hn> <at>
sr|dhm|<dn> <hn>:<mN> <at>
sr|dHm|<dn> <hN>:<mN>
sr|dhmz|<dn> <hn>:<mN> <at> <zt>
sr|dHms|<dn> <hN>:<mN>:<sN>
sr|dHmzzzz|<dn> <hN>:<mN> <zt>
sr|MMMMdh|<dn>. <Mt> <hn> <at>
sr|MMMMdhm|<dn>. <Mt> <hn>:<mN> <at>
sr|MMMMdHm|<dn>. <Mt> <hN>:<mN>
sr|MMMMdhmz|<dn>. <Mt> <hn>:<mN> <at> <zt>
sr|MMMMdHms|<dn>. <Mt> <hN>:<mN>:<sN>
sr|MMMMdHmzzzz|<dn>. <Mt> <hN>:<mN> <zt>
sr|yMMMMEEEEdh|<Et>, <dn>. <Mt> <yn>. <hn> <at>
sr|yMMMMEEEEdhm|<Et>, <dn>. <Mt> <yn>. <hn>:<mN> <at>
sr|yMMMMEEEEdHm|<Et>, <dn>. <Mt> <yn>. <hN>:<mN>
sr|yMMMMEEEEdhmz|<Et>, <dn>. <Mt> <yn>. <hn>:<mN> <at> <zt>
sr|yMMMMEEEEdHms|<Et>, <dn>. <Mt> <yn>. <hN>:<mN>:<sN>
sr|yMMMMEEEEdHmzzzz|<Et>, <dn>. <Mt> <yn>. <hN>:<mN> <zt>
sr|EEEEdh|<Et> <dn>. <hn> <at>
sr|EEEEdhm|<Et> <dn>. <hn>:<mN> <at>
sr|EEEEdHm|<Et> <dn>. <hN>:<mN>
sr|EEEEdhmz|<Et> <dn>. <hn>:<mN> <at> <zt>
sr|EEEEdHms|<Et> <dn>. <hN>:<mN>:<sN>
sr|EEEEdHmzzzz|<Et> <dn>. <hN>:<mN> <zt>
sv|d|<dn>
sv|E|<Et>
sv|Ed|<Et> <dn>
sv|EEEE|<Et>
sv|EEEEd|<Et> <dn>
sv|M|<Mn>
sv|Md|<dn>/<Mn>
sv|ME|<Mn> <Et>
sv|MEd|<Et> <dn>/<Mn>
sv|MEEEE|<Mn> <Et>
sv|MEEEEd|<Et> <dn>/<Mn>
sv|MM|<MN>
sv|MMd|<dn>/<Mn>
sv|MME|<Et> (månad: <MN>)
sv|MMEd|<Et> <dn>/<MN>
sv|MMEEEE|<MN> <Et>
sv|MMEEEEd|<Et> <dn>/<MN>
sv|MMM|<Mt>
sv|MMMd|<dn> <Mt>
sv|MMME|<Mt> <Et>
sv|MMMEd|<Et> <dn> <Mt>
sv|MMMEEEE|<Mt> <Et>
sv|MMMEEEEd|<Et> <dn> <Mt>
sv|MMMM|<Mt>
sv|MMMMd|<dn> <Mt>
sv|MMMME|<Et> (månad: <Mt>)
sv|MMMMEd|<Et> <dn> <Mt>
sv|MMMMEEEE|<Mt> <Et>
sv|MMMMEEEEd|<Et> <dn> <Mt>
sv|y|<yn>
sv|yd|<yn> (dag: <dn>)
sv|yE|<yn> <Et>
sv|yEd|<yn> <Et> <dn>
sv|yEEEE|<yn> <Et>
sv|yEEEEd|<yn> <Et> <dn>
sv|yM|<yn>-<MN>
sv|yMd|<yn>-<MN>-<dN>
sv|yME|<yn>-<MN> <Et>
sv|yMEd|<Et>, <yn>-<MN>-<dN>
sv|yMEEEE|<yn>-<MN> <Et>
sv|yMEEEEd|<Et>, <yn>-<MN>-<dN>
sv|yMM|<yn>-<MN>
sv|yMMd|<yn>-<MN>-<dn>
sv|yMME|<yn>-<MN> <Et>
sv|yMMEd|<Et>, <yn>-<MN>-<dN>
sv|yMMEEEE|<yn>-<MN> <Et>
sv|yMMEEEEd|<Et>, <yn>-<MN>-<dN>
sv|yMMM|<Mt> <yn>
sv|yMMMd|<dn> <Mt> <yn>
sv|yMMME|<Mt> <yn> <Et>
sv|yMMMEd|<Et> <dn> <Mt> <yn>
sv|yMMMEEEE|<Mt> <yn> <Et>
sv|yMMMEEEEd|<Et> <dn> <Mt> <yn>
sv|yMMMM|<Mt> <yn>
sv|yMMMMd|<dn> <Mt> <yn>
sv|yMMMME|<Mt> <yn> <Et>
sv|yMMMMEd|<Et> <dn> <Mt> <yn>
sv|yMMMMEEEE|<Mt> <yn> <Et>
sv|yMMMMEEEEd|<Et> <dn> <Mt> <yn>
sv|Gy|<yn> <Gt>
sv|GyMMMd|<dn> <Mt> <yn> <Gt>
sv|GyMMMMEEEEd|<Et> <dn> <Mt> <yn> <Gt>
sv|GyMd|<yn>-<MN>-<dN> <Gt>
sv|s|<sn>
sv|sz|<sn> <zt>
sv|szzzz|<sn> <zt>
sv|m|<mn>
sv|mz|<mn> <zt>
sv|mzzzz|<mn> <zt>
sv|ms|<mN>:<sN>
sv|msz|<mN>:<sN> <zt>
sv|mszzzz|<mN>:<sN> <zt>
sv|h|<hn> <at>
sv|hz|<hn> <at> <zt>
sv|hzzzz|<hn> <at> <zt>
sv|hs|<hn> <at> (sekund: <sn>)
sv|hsz|<hn> <at> <zt> (sekund: <sn>)
sv|hszzzz|<hn> <at> <zt> (sekund: <sn>)
sv|hm|<hn>:<mN> <at>
sv|hmz|<hn>:<mN> <at> <zt>
sv|hmzzzz|<hn>:<mN> <at> <zt>
sv|hms|<hn>:<mN>:<sN> <at>
sv|hmsz|<hn>:<mN>:<sN> <at> <zt>
sv|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
sv|H|<hN>
sv|Hz|<hN>h <zt>
sv|Hzzzz|<hN>h <zt>
sv|Hs|<hN> (sekund: <sn>)
sv|Hsz|<hN>h <zt> (sekund: <sn>)
sv|Hszzzz|<hN>h <zt> (sekund: <sn>)
sv|Hm|<hN>:<mN>
sv|Hmz|<hN>:<mN> <zt>
sv|Hmzzzz|<hN>:<mN> <zt>
sv|Hms|<hN>:<mN>:<sN>
sv|Hmsz|<hn>:<mN>:<sN> <zt>
sv|Hmszzzz|<hn>:<mN>:<sN> <zt>
sv|yMMMdh|<dn> <Mt> <yn> <hn> <at>
sv|yMMMdhm|<dn> <Mt> <yn> <hn>:<mN> <at>
sv|yMMMdHm|<dn> <Mt> <yn> <hN>:<mN>
sv|yMMMdhmz|<dn> <Mt> <yn> <hn>:<mN> <at> <zt>
sv|yMMMdHms|<dn> <Mt> <yn> <hN>:<mN>:<sN>
sv|yMMMdHmzzzz|<dn> <Mt> <yn> <hN>:<mN> <zt>
sv|MMMEdh|<Et> <dn> <Mt> <hn> <at>
sv|MMMEdhm|<Et> <dn> <Mt> <hn>:<mN> <at>
sv|MMMEdHm|<Et> <dn> <Mt> <hN>:<mN>
sv|MMMEdhmz|<Et> <dn> <Mt> <hn>:<mN> <at> <zt>
sv|MMMEdHms|<Et> <dn> <Mt> <hN>:<mN>:<sN>
sv|MMMEdHmzzzz|<Et> <dn> <Mt> <hN>:<mN> <zt>
sv|yMdh|<yn>-<MN>-<dN> <hn> <at>
sv|yMdhm|<yn>-<MN>-<dN> <hn>:<mN> <at>
sv|yMdHm|<yn>-<MN>-<dN> <hN>:<mN>
sv|yMdhmz|<yn>-<MN>-<dN> <hn>:<mN> <at> <zt>
sv|yMdHms|<yn>-<MN>-<dN> <hN>:<mN>:<sN>
sv|yMdHmzzzz|<yn>-<MN>-<dN> <hN>:<mN> <zt>
sv|Eh|<Et> <hn> <at>
sv|Ehm|<Et> <hn>:<mN> <at>
sv|EHm|<Et> <hN>:<mN>
sv|Ehmz|<Et> <hn>:<mN> <at> <zt>
sv|EHms|<Et> <hN>:<mN>:<sN>
sv|EHmzzzz|<Et> <hN>:<mN> <zt>
sv|dh|<dn> <hn> <at>
sv|dhm|<dn> <hn>:<mN> <at>
sv|dHm|<dn> <hN>:<mN>
sv|dhmz|<dn> <hn>:<mN> <at> <zt>
sv|dHms|<dn> <hN>:<mN>:<sN>
sv|dHmzzzz|<dn> <hN>:<mN> <zt>
sv|MMMMdh|<dn> <Mt> kl. <hn> <at>
sv|MMMMdhm|<dn> <Mt> kl. <hn>:<mN> <at>
sv|MMMMdHm|<dn> <Mt> kl. <hN>:<mN>
sv|MMMMdhmz|<dn> <Mt> kl. <hn>:<mN> <at> <zt>
sv|MMMMdHms|<dn> <Mt> kl. <hN>:<mN>:<sN>
sv|MMMMdHmzzzz|<dn> <Mt> kl. <hN>:<mN> <zt>
sv|yMMMMEEEEdh|<Et> <dn> <Mt> <yn> kl. <hn> <at>
sv|yMMMMEEEEdhm|<Et> <dn> <Mt> <yn> kl. <hn>:<mN> <at>
sv|yMMMMEEEEdHm|<Et> <dn> <Mt> <yn> kl. <hN>:<mN>
sv|yMMMMEEEEdhmz|<Et> <dn> <Mt> <yn> kl. <hn>:<mN> <at> <zt>
sv|yMMMMEEEEdHms|<Et> <dn> <Mt> <yn> kl. <hN>:<mN>:<sN>
sv|yMMMMEEEEdHmzzzz|<Et> <dn> <Mt> <yn> kl. <hN>:<mN> <zt>
sv|EEEEdh|<Et> <dn> <hn> <at>
sv|EEEEdhm|<Et> <dn> <hn>:<mN> <at>
sv|EEEEdHm|<Et> <dn> <hN>:<mN>
sv|EEEEdhmz|<Et> <dn> <hn>:<mN> <at> <zt>
sv|EEEEdHms|<Et> <dn> <hN>:<mN>:<sN>
sv|EEEEdHmzzzz|<Et> <dn> <hN>:<mN> <zt>
sw|d|<dn>
sw|E|<Et>
sw|Ed|<Et> <dn>
sw|EEEE|<Et>
sw|EEEEd|<Et> <dn>
sw|M|<Mn>
sw|Md|<dn>/<Mn>
sw|ME|<Mn> <Et>
sw|MEd|<Et>, <dn>/<Mn>
sw|MEEEE|<Mn> <Et>
sw|MEEEEd|<Et>, <dn>/<Mn>
sw|MM|<MN>
sw|MMd|<dn>/<MN>
sw|MME|<Et> (mwezi: <MN>)
sw|MMEd|<Et>, <dn>/<MN>
sw|MMEEEE|<MN> <Et>
sw|MMEEEEd|<Et>, <dn>/<MN>
sw|MMM|<Mt>
sw|MMMd|<dn> <Mt>
sw|MMME|<Mt> <Et>
sw|MMMEd|<Et>, <dn> <Mt>
sw|MMMEEEE|<Mt> <Et>
sw|MMMEEEEd|<Et>, <dn> <Mt>
sw|MMMM|<Mt>
sw|MMMMd|<dn> <Mt>
sw|MMMME|<Et> (mwezi: <Mt>)
sw|MMMMEd|<Et>, <dn> <Mt>
sw|MMMMEEEE|<Mt> <Et>
sw|MMMMEEEEd|<Et>, <dn> <Mt>
sw|y|<yn>
sw|yd|<yn> (siku: <dn>)
sw|yE|<yn> <Et>
sw|yEd|<yn> <Et> <dn>
sw|yEEEE|<yn> <Et>
sw|yEEEEd|<yn> <Et> <dn>
sw|yM|<Mn>/<yn>
sw|yMd|<dn>/<Mn>/<yn>
sw|yME|<Mn>/<yn> <Et>
sw|yMEd|<Et>, <dn>/<Mn>/<yn>
sw|yMEEEE|<Mn>/<yn> <Et>
sw|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
sw|yMM|<MN>/<yn>
sw|yMMd|<dn>/<MN>/<yn>
sw|yMME|<MN>/<yn> <Et>
sw|yMMEd|<Et>, <dn>/<MN>/<yn>
sw|yMMEEEE|<MN>/<yn> <Et>
sw|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
sw|yMMM|<Mt> <yn>
sw|yMMMd|<dn> <Mt> <yn>
sw|yMMME|<Mt> <yn> <Et>
sw|yMMMEd|<Et>, <dn> <Mt> <yn>
sw|yMMMEEEE|<Mt> <yn> <Et>
sw|yMMMEEEEd|<Et>, <dn> <Mt> <yn>
sw|yMMMM|<Mt> <yn>
sw|yMMMMd|<dn> <Mt> <yn>
sw|yMMMME|<Mt> <yn> <Et>
sw|yMMMMEd|<Et>, <dn> <Mt> <yn>
sw|yMMMMEEEE|<Mt> <yn> <Et>
sw|yMMMMEEEEd|<Et>, <dn> <Mt> <yn>
sw|Gy|<yn> <Gt>
sw|GyMMMd|<dn> <Mt> <yn> <Gt>
sw|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> <Gt>
sw|GyMd|<dn>/<Mn>/<yn> <Gt>
sw|s|<sn>
sw|sz|<sn> <zt>
sw|szzzz|<sn> <zt>
sw|m|<mn>
sw|mz|<mn> <zt>
sw|mzzzz|<mn> <zt>
sw|ms|<mN>:<sN>
sw|msz|<mN>:<sN> <zt>
sw|mszzzz|<mN>:<sN> <zt>
sw|h|<hn> <at>
sw|hz|<hn> <at> <zt>
sw|hzzzz|<hn> <at> <zt>
sw|hs|<hn> <at> (sekunde: <sn>)
sw|hsz|<hn> <at> <zt> (sekunde: <sn>)
sw|hszzzz|<hn> <at> <zt> (sekunde: <sn>)
sw|hm|<hn>:<mN> <at>
sw|hmz|<hn>:<mN> <at> <zt>
sw|hmzzzz|<hn>:<mN> <at> <zt>
sw|hms|<hn>:<mN>:<sN> <at>
sw|hmsz|<hn>:<mN>:<sN> <at> <zt>
sw|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
sw|H|<hN>
sw|Hz|<hN>h <zt>
sw|Hzzzz|<hN>h <zt>
sw|Hs|<hN> (sekunde: <sn>)
sw|Hsz|<hN>h <zt> (sekunde: <sn>)
sw|Hszzzz|<hN>h <zt> (sekunde: <sn>)
sw|Hm|<hN>:<mN>
sw|Hmz|<hN>:<mN> <zt>
sw|Hmzzzz|<hN>:<mN> <zt>
sw|Hms|<hN>:<mN>:<sN>
sw|Hmsz|<hn>:<mN>:<sN> <zt>
sw|Hmszzzz|<hn>:<mN>:<sN> <zt>
sw|yMMMdh|<dn> <Mt> <yn>, <hn> <at>
sw|yMMMdhm|<dn> <Mt> <yn>, <hn>:<mN> <at>
sw|yMMMdHm|<dn> <Mt> <yn>, <hN>:<mN>
sw|yMMMdhmz|<dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
sw|yMMMdHms|<dn> <Mt> <yn>, <hN>:<mN>:<sN>
sw|yMMMdHmzzzz|<dn> <Mt> <yn>, <hN>:<mN> <zt>
sw|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
sw|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
sw|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
sw|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
sw|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
sw|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
sw|yMdh|<dn>/<Mn>/<yn>, <hn> <at>
sw|yMdhm|<dn>/<Mn>/<yn>, <hn>:<mN> <at>
sw|yMdHm|<dn>/<Mn>/<yn>, <hN>:<mN>
sw|yMdhmz|<dn>/<Mn>/<yn>, <hn>:<mN> <at> <zt>
sw|yMdHms|<dn>/<Mn>/<yn>, <hN>:<mN>:<sN>
sw|yMdHmzzzz|<dn>/<Mn>/<yn>, <hN>:<mN> <zt>
sw|Eh|<Et> <hn> <at>
sw|Ehm|<Et> <hn>:<mN> <at>
sw|EHm|<Et> <hN>:<mN>
sw|Ehmz|<Et>, <hn>:<mN> <at> <zt>
sw|EHms|<Et> <hN>:<mN>:<sN>
sw|EHmzzzz|<Et>, <hN>:<mN> <zt>
sw|dh|<dn>, <hn> <at>
sw|dhm|<dn>, <hn>:<mN> <at>
sw|dHm|<dn>, <hN>:<mN>
sw|dhmz|<dn>, <hn>:<mN> <at> <zt>
sw|dHms|<dn>, <hN>:<mN>:<sN>
sw|dHmzzzz|<dn>, <hN>:<mN> <zt>
sw|MMMMdh|<dn> <Mt>, <hn> <at>
sw|MMMMdhm|<dn> <Mt>, <hn>:<mN> <at>
sw|MMMMdHm|<dn> <Mt>, <hN>:<mN>
sw|MMMMdhmz|<dn> <Mt>, <hn>:<mN> <at> <zt>
sw|MMMMdHms|<dn> <Mt>, <hN>:<mN>:<sN>
sw|MMMMdHmzzzz|<dn> <Mt>, <hN>:<mN> <zt>
sw|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn>, <hn> <at>
sw|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn>, <hn>:<mN> <at>
sw|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn>, <hN>:<mN>
sw|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn>, <hn>:<mN> <at> <zt>
sw|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn>, <hN>:<mN>:<sN>
sw|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn>, <hN>:<mN> <zt>
sw|EEEEdh|<Et> <dn>, <hn> <at>
sw|EEEEdhm|<Et> <dn>, <hn>:<mN> <at>
sw|EEEEdHm|<Et> <dn>, <hN>:<mN>
sw|EEEEdhmz|<Et> <dn>, <hn>:<mN> <at> <zt>
sw|EEEEdHms|<Et> <dn>, <hN>:<mN>:<sN>
sw|EEEEdHmzzzz|<Et> <dn>, <hN>:<mN> <zt>
th|d|<dn>
th|E|<Et>
th|Ed|<Et> <dn>
th|EEEE|<Et>
th|EEEEd|<Et> <dn>
th|M|<Mn>
th|Md|<dn>/<Mn>
th|ME|<Mn> <Et>
th|MEd|<Et> <dn>/<Mn>
th|MEEEE|<Mn> <Et>
th|MEEEEd|<Et> <dn>/<Mn>
th|MM|<MN>
th|MMd|<dn>/<MN>
th|MME|<Et> (เดือน: <MN>)
th|MMEd|<Et> <dn>/<MN>
th|MMEEEE|<MN> <Et>
th|MMEEEEd|<Et> <dn>/<MN>
th|MMM|<Mt>
th|MMMd|<dn> <Mt>
th|MMME|<Mt> <Et>
th|MMMEd|<Et> <dn> <Mt>
th|MMMEEEE|<Mt> <Et>
th|MMMEEEEd|<Et>ที่ <dn> <Mt>
th|MMMM|<Mt>
th|MMMMd|<dn> <Mt>
th|MMMME|<Et> (เดือน: <Mt>)
th|MMMMEd|<Et> <dn> <Mt>
th|MMMMEEEE|<Mt> <Et>
th|MMMMEEEEd|<Et>ที่ <dn> <Mt>
th|y|<yn>
th|yd|<yn> (วัน: <dn>)
th|yE|<yn> <Et>
th|yEd|<yn> <Et> <dn>
th|yEEEE|<yn> <Et>
th|yEEEEd|<yn> <Et> <dn>
th|yM|<Mn>/<yn>
th|yMd|<dn>/<Mn>/<yn>
th|yME|<Mn>/<yn> <Et>
th|yMEd|<Et> <dn>/<Mn>/<yn>
th|yMEEEE|<Mn>/<yn> <Et>
th|yMEEEEd|<Et> <dn>/<Mn>/<yn>
th|yMM|<MN>/<yn>
th|yMMd|<dn>/<MN>/<yn>
th|yMME|<MN>/<yn> <Et>
th|yMMEd|<Et> <dn>/<MN>/<yn>
th|yMMEEEE|<MN>/<yn> <Et>
th|yMMEEEEd|<Et> <dn>/<MN>/<yn>
th|yMMM|<Mt> <yn>
th|yMMMd|<dn> <Mt> <yn>
th|yMMME|<Mt> <yn> <Et>
th|yMMMEd|<Et> <dn> <Mt> <yn>
th|yMMMEEEE|<Mt> <yn> <Et>
th|yMMMEEEEd|<Et>ที่ <dn> <Mt> <yn>
th|yMMMM|<Mt> <yn>
th|yMMMMd|<dn> <Mt> <yn>
th|yMMMME|<Mt> <yn> <Et>
th|yMMMMEd|<Et> <dn> <Mt> <yn>
th|yMMMMEEEE|<Mt> <yn> <Et>
th|yMMMMEEEEd|<Et>ที่ <dn> <Mt> <yn>
th|Gy|<Gt> <yn>
th|GyMMMd|<dn> <Mt> <Gt> <yn>
th|GyMMMMEEEEd|<Et>ที่ <dn> <Mt> <Gt> <yn>
th|GyMd|<dn>/<Mn>/<Gt> <yn>
th|s|<sn>
th|sz|<sn> <zt>
th|szzzz|<sn> <zt>
th|m|<mn>
th|mz|<mn> <zt>
th|mzzzz|<mn> <zt>
th|ms|<mN>:<sN>
th|msz|<mN>:<sN> <zt>
th|mszzzz|<mN>:<sN> <zt>
th|h|<hn> <at>
th|hz|<hn> <at> <zt>
th|hzzzz|<hn> <at> <zt>
th|hs|<hn> <at> (วินาที: <sn>)
th|hsz|<hn> <at> <zt> (วินาที: <sn>)
th|hszzzz|<hn> <at> <zt> (วินาที: <sn>)
th|hm|<hn>:<mN> <at>
th|hmz|<hn>:<mN> น. <at> <zt>
th|hmzzzz|<hn>:<mN> น. <at> <zt>
th|hms|<hn>:<mN>:<sN> <at>
th|hmsz|<hn>:<mN>:<sN> <at> <zt>
th|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
th|H|<hN>
th|Hz|<hN>h <zt>
th|Hzzzz|<hN>h <zt>
th|Hs|<hN> (วินาที: <sn>)
th|Hsz|<hN>h <zt> (วินาที: <sn>)
th|Hszzzz|<hN>h <zt> (วินาที: <sn>)
th|Hm|<hN>:<mN> น.
th|Hmz|<hN>:<mN> <zt>
th|Hmzzzz|<hN>:<mN> <zt>
th|Hms|<hN>:<mN>:<sN>
th|Hmsz|<hn> นาฬิกา <mN> นาที <sN> วินาที <zt>
th|Hmszzzz|<hn> นาฬิกา <mN> นาที <sN> วินาที <zt>
th|yMMMdh|<dn> <Mt> <yn> <hn> <at>
th|yMMMdhm|<dn> <Mt> <yn> <hn>:<mN> <at>
th|yMMMdHm|<dn> <Mt> <yn> <hN>:<mN> น.
th|yMMMdhmz|<dn> <Mt> <yn> <hn>:<mN> น. <at> <zt>
th|yMMMdHms|<dn> <Mt> <yn> <hN>:<mN>:<sN>
th|yMMMdHmzzzz|<dn> <Mt> <yn> <hN>:<mN> <zt>
th|MMMEdh|<Et> <dn> <Mt> <hn> <at>
th|MMMEdhm|<Et> <dn> <Mt> <hn>:<mN> <at>
th|MMMEdHm|<Et> <dn> <Mt> <hN>:<mN> น.
th|MMMEdhmz|<Et> <dn> <Mt> <hn>:<mN> น. <at> <zt>
th|MMMEdHms|<Et> <dn> <Mt> <hN>:<mN>:<sN>
th|MMMEdHmzzzz|<Et> <dn> <Mt> <hN>:<mN> <zt>
th|yMdh|<dn>/<Mn>/<yn> <hn> <at>
th|yMdhm|<dn>/<Mn>/<yn> <hn>:<mN> <at>
th|yMdHm|<dn>/<Mn>/<yn> <hN>:<mN> น.
th|yMdhmz|<dn>/<Mn>/<yn> <hn>:<mN> น. <at> <zt>
th|yMdHms|<dn>/<Mn>/<yn> <hN>:<mN>:<sN>
th|yMdHmzzzz|<dn>/<Mn>/<yn> <hN>:<mN> <zt>
th|Eh|<Et> <hn> <at>
th|Ehm|<Et> <hn>:<mN> <at>
th|EHm|<Et> <hN>:<mN> น.
th|Ehmz|<Et> <hn>:<mN> น. <at> <zt>
th|EHms|<Et> <hN>:<mN>:<sN>
th|EHmzzzz|<Et> <hN>:<mN> <zt>
th|dh|<dn> <hn> <at>
th|dhm|<dn> <hn>:<mN> <at>
th|dHm|<dn> <hN>:<mN> น.
th|dhmz|<dn> <hn>:<mN> น. <at> <zt>
th|dHms|<dn> <hN>:<mN>:<sN>
th|dHmzzzz|<dn> <hN>:<mN> <zt>
th|MMMMdh|<dn> <Mt> เวลา <hn> <at>
th|MMMMdhm|<dn> <Mt> เวลา <hn>:<mN> <at>
th|MMMMdHm|<dn> <Mt> เวลา <hN>:<mN> น.
th|MMMMdhmz|<dn> <Mt> เวลา <hn>:<mN> น. <at> <zt>
th|MMMMdHms|<dn> <Mt> เวลา <hN>:<mN>:<sN>
th|MMMMdHmzzzz|<dn> <Mt> เวลา <hN>:<mN> <zt>
th|yMMMMEEEEdh|<Et>ที่ <dn> <Mt> <yn> เวลา <hn> <at>
th|yMMMMEEEEdhm|<Et>ที่ <dn> <Mt> <yn> เวลา <hn>:<mN> <at>
th|yMMMMEEEEdHm|<Et>ที่ <dn> <Mt> <yn> เวลา <hN>:<mN> น.
th|yMMMMEEEEdhmz|<Et>ที่ <dn> <Mt> <yn> เวลา <hn>:<mN> น. <at> <zt>
th|yMMMMEEEEdHms|<Et>ที่ <dn> <Mt> <yn> เวลา <hN>:<mN>:<sN>
th|yMMMMEEEEdHmzzzz|<Et>ที่ <dn> <Mt> <yn> เวลา <hN>:<mN> <zt>
th|EEEEdh|<Et> <dn> <hn> <at>
th|EEEEdhm|<Et> <dn> <hn>:<mN> <at>
th|EEEEdHm|<Et> <dn> <hN>:<mN> น.
th|EEEEdhmz|<Et> <dn> <hn>:<mN> น. <at> <zt>
th|EEEEdHms|<Et> <dn> <hN>:<mN>:<sN>
th|EEEEdHmzzzz|<Et> <dn> <hN>:<mN> <zt>
tr|d|<dn>
tr|E|<Et>
tr|Ed|<dn> <Et>
tr|EEEE|<Et>
tr|EEEEd|<dn> <Et>
tr|M|<Mn>
tr|Md|<dn>/<Mn>
tr|ME|<Mn> <Et>
tr|MEd|<dn>/<Mn> <Et>
tr|MEEEE|<Mn> <Et>
tr|MEEEEd|<dn>/<Mn> <Et>
tr|MM|<MN>
tr|MMd|<dn>/<MN>
tr|MME|<Et> (ay: <MN>)
tr|MMEd|<dn>/<MN> <Et>
tr|MMEEEE|<MN> <Et>
tr|MMEEEEd|<dn>/<MN> <Et>
tr|MMM|<Mt>
tr|MMMd|<dn> <Mt>
tr|MMME|<Mt> <Et>
tr|MMMEd|<dn> <Mt> <Et>
tr|MMMEEEE|<Mt> <Et>
tr|MMMEEEEd|<dn> <Mt> <Et>
tr|MMMM|<Mt>
tr|MMMMd|<dn> <Mt>
tr|MMMME|<Et> (ay: <Mt>)
tr|MMMMEd|<dn> <Mt> <Et>
tr|MMMMEEEE|<Mt> <Et>
tr|MMMMEEEEd|<dn> <Mt> <Et>
tr|y|<yn>
tr|yd|<yn> (gün: <dn>)
tr|yE|<yn> <Et>
tr|yEd|<yn> <dn> <Et>
tr|yEEEE|<yn> <Et>
tr|yEEEEd|<yn> <dn> <Et>
tr|yM|<MN>/<yn>
tr|yMd|<dN>.<MN>.<yn>
tr|yME|<MN>/<yn> <Et>
tr|yMEd|<dn>.<Mn>.<yn> <Et>
tr|yMEEEE|<MN>/<yn> <Et>
tr|yMEEEEd|<dn>.<Mn>.<yn> <Et>
tr|yMM|<MN>.<yn>
tr|yMMd|<dn>.<MN>.<yn>
tr|yMME|<MN>.<yn> <Et>
tr|yMMEd|<dn>.<MN>.<yn> <Et>
tr|yMMEEEE|<MN>.<yn> <Et>
tr|yMMEEEEd|<dn>.<MN>.<yn> <Et>
tr|yMMM|<Mt> <yn>
tr|yMMMd|<dn> <Mt> <yn>
tr|yMMME|<Mt> <yn> <Et>
tr|yMMMEd|<dn> <Mt> <yn> <Et>
tr|yMMMEEEE|<Mt> <yn> <Et>
tr|yMMMEEEEd|<dn> <Mt> <yn> <Et>
tr|yMMMM|<Mt> <yn>
tr|yMMMMd|<dn> <Mt> <yn>
tr|yMMMME|<Mt> <yn> <Et>
tr|yMMMMEd|<dn> <Mt> <yn> <Et>
tr|yMMMMEEEE|<Mt> <yn> <Et>
tr|yMMMMEEEEd|<dn> <Mt> <yn> <Et>
tr|Gy|<Gt> <yn>
tr|GyMMMd|<Gt> <dn> <Mt> <yn>
tr|GyMMMMEEEEd|<Gt> <dn> <Mt> <yn> <Et>
tr|GyMd|<Gt> <dN>.<MN>.<yn>
tr|s|<sn>
tr|sz|<sn> <zt>
tr|szzzz|<sn> <zt>
tr|m|<mn>
tr|mz|<mn> <zt>
tr|mzzzz|<mn> <zt>
tr|ms|<mN>:<sN>
tr|msz|<mN>:<sN> <zt>
tr|mszzzz|<mN>:<sN> <zt>
tr|h|<at> <hn>
tr|hz|<hn> <at> <zt>
tr|hzzzz|<hn> <at> <zt>
tr|hs|<at> <hn> (saniye: <sn>)
tr|hsz|<hn> <at> <zt> (saniye: <sn>)
tr|hszzzz|<hn> <at> <zt> (saniye: <sn>)
tr|hm|<at> <hn>:<mN>
tr|hmz|<at> <hn>:<mN> <zt>
tr|hmzzzz|<at> <hn>:<mN> <zt>
tr|hms|<at> <hn>:<mN>:<sN>
tr|hmsz|<at> <hn>:<mN>:<sN> <zt>
tr|hmszzzz|<at> <hn>:<mN>:<sN> <zt>
tr|H|<hN>
tr|Hz|<hN> <zt>
tr|Hzzzz|<hN> <zt>
tr|Hs|<hN> (saniye: <sn>)
tr|Hsz|<hN> <zt> (saniye: <sn>)
tr|Hszzzz|<hN> <zt> (saniye: <sn>)
tr|Hm|<hN>:<mN>
tr|Hmz|<hN>:<mN> <zt>
tr|Hmzzzz|<hN>:<mN> <zt>
tr|Hms|<hN>:<mN>:<sN>
tr|Hmsz|<hn>:<mN>:<sN> <zt>
tr|Hmszzzz|<hn>:<mN>:<sN> <zt>
tr|yMMMdh|<dn> <Mt> <yn> <at> <hn>
tr|yMMMdhm|<dn> <Mt> <yn> <at> <hn>:<mN>
tr|yMMMdHm|<dn> <Mt> <yn> <hN>:<mN>
tr|yMMMdhmz|<dn> <Mt> <yn> <at> <hn>:<mN> <zt>
tr|yMMMdHms|<dn> <Mt> <yn> <hN>:<mN>:<sN>
tr|yMMMdHmzzzz|<dn> <Mt> <yn> <hN>:<mN> <zt>
tr|MMMEdh|<dn> <Mt> <Et> <at> <hn>
tr|MMMEdhm|<dn> <Mt> <Et> <at> <hn>:<mN>
tr|MMMEdHm|<dn> <Mt> <Et> <hN>:<mN>
tr|MMMEdhmz|<dn> <Mt> <Et> <at> <hn>:<mN> <zt>
tr|MMMEdHms|<dn> <Mt> <Et> <hN>:<mN>:<sN>
tr|MMMEdHmzzzz|<dn> <Mt> <Et> <hN>:<mN> <zt>
tr|yMdh|<dN>.<MN>.<yn> <at> <hn>
tr|yMdhm|<dN>.<MN>.<yn> <at> <hn>:<mN>
tr|yMdHm|<dN>.<MN>.<yn> <hN>:<mN>
tr|yMdhmz|<dN>.<MN>.<yn> <at> <hn>:<mN> <zt>
tr|yMdHms|<dN>.<MN>.<yn> <hN>:<mN>:<sN>
tr|yMdHmzzzz|<dN>.<MN>.<yn> <hN>:<mN> <zt>
tr|Eh|<Et> <hn> <at>
tr|Ehm|<Et> <at> <hn>:<mN>
tr|EHm|<Et> <hN>:<mN>
tr|Ehmz|<Et> <at> <hn>:<mN> <zt>
tr|EHms|<Et> <hN>:<mN>:<sN>
tr|EHmzzzz|<Et> <hN>:<mN> <zt>
tr|dh|<dn> <at> <hn>
tr|dhm|<dn> <at> <hn>:<mN>
tr|dHm|<dn> <hN>:<mN>
tr|dhmz|<dn> <at> <hn>:<mN> <zt>
tr|dHms|<dn> <hN>:<mN>:<sN>
tr|dHmzzzz|<dn> <hN>:<mN> <zt>
tr|MMMMdh|<dn> <Mt> <at> <hn>
tr|MMMMdhm|<dn> <Mt> <at> <hn>:<mN>
tr|MMMMdHm|<dn> <Mt> <hN>:<mN>
tr|MMMMdhmz|<dn> <Mt> <at> <hn>:<mN> <zt>
tr|MMMMdHms|<dn> <Mt> <hN>:<mN>:<sN>
tr|MMMMdHmzzzz|<dn> <Mt> <hN>:<mN> <zt>
tr|yMMMMEEEEdh|<dn> <Mt> <yn> <Et> <at> <hn>
tr|yMMMMEEEEdhm|<dn> <Mt> <yn> <Et> <at> <hn>:<mN>
tr|yMMMMEEEEdHm|<dn> <Mt> <yn> <Et> <hN>:<mN>
tr|yMMMMEEEEdhmz|<dn> <Mt> <yn> <Et> <at> <hn>:<mN> <zt>
tr|yMMMMEEEEdHms|<dn> <Mt> <yn> <Et> <hN>:<mN>:<sN>
tr|yMMMMEEEEdHmzzzz|<dn> <Mt> <yn> <Et> <hN>:<mN> <zt>
tr|EEEEdh|<dn> <Et> <at> <hn>
tr|EEEEdhm|<dn> <Et> <at> <hn>:<mN>
tr|EEEEdHm|<dn> <Et> <hN>:<mN>
tr|EEEEdhmz|<dn> <Et> <at> <hn>:<mN> <zt>
tr|EEEEdHms|<dn> <Et> <hN>:<mN>:<sN>
tr|EEEEdHmzzzz|<dn> <Et> <hN>:<mN> <zt>
uk|d|<dn>
uk|E|<Et>
uk|Ed|<Et>, <dn>
uk|EEEE|<Et>
uk|EEEEd|<Et>, <dn>
uk|M|<MN>
uk|Md|<dN>.<MN>
uk|ME|<MN> <Et>
uk|MEd|<Et>, <dN>.<MN>
uk|MEEEE|<MN> <Et>
uk|MEEEEd|<Et>, <dN>.<MN>
uk|MM|<MN>
uk|MMd|<dN>.<MN>
uk|MME|<Et> (місяць: <MN>)
uk|MMEd|<Et>, <dN>.<MN>
uk|MMEEEE|<MN> <Et>
uk|MMEEEEd|<Et>, <dN>.<MN>
uk|MMM|<Mt>
uk|MMMd|<dn> <Mt>
uk|MMME|<Mt> <Et>
uk|MMMEd|<Et>, <dn> <Mt>
uk|MMMEEEE|<Mt> <Et>
uk|MMMEEEEd|<Et>, <dn> <Mt>
uk|MMMM|<Mt>
uk|MMMMd|<dn> <Mt>
uk|MMMME|<Et> (місяць: <Mt>)
uk|MMMMEd|<Et>, <dn> <Mt>
uk|MMMMEEEE|<Mt> <Et>
uk|MMMMEEEEd|<Et>, <dn> <Mt>
uk|y|<yn>
uk|yd|<yn> (день: <dn>)
uk|yE|<yn> <Et>
uk|yEd|<yn> <Et>, <dn>
uk|yEEEE|<yn> <Et>
uk|yEEEEd|<yn> <Et>, <dn>
uk|yM|<MN>.<yn>
uk|yMd|<dN>.<MN>.<yn>
uk|yME|<MN>.<yn> <Et>
uk|yMEd|<Et>, <dN>.<MN>.<yn>
uk|yMEEEE|<MN>.<yn> <Et>
uk|yMEEEEd|<Et>, <dN>.<MN>.<yn>
uk|yMM|<MN>.<yn>
uk|yMMd|<dN>.<MN>.<yn>
uk|yMME|<MN>.<yn> <Et>
uk|yMMEd|<Et>, <dN>.<MN>.<yn>
uk|yMMEEEE|<MN>.<yn> <Et>
uk|yMMEEEEd|<Et>, <dN>.<MN>.<yn>
uk|yMMM|<Mt> <yn> р.
uk|yMMMd|<dn> <Mt> <yn> р.
uk|yMMME|<Mt> <yn> р. <Et>
uk|yMMMEd|<Et>, <dn> <Mt> <yn> р.
uk|yMMMEEEE|<Mt> <yn> р. <Et>
uk|yMMMEEEEd|<Et>, <dn> <Mt> <yn> р.
uk|yMMMM|<Mt> <yn> р.
uk|yMMMMd|<dn> <Mt> <yn> р.
uk|yMMMME|<Mt> <yn> р. <Et>
uk|yMMMMEd|<Et>, <dn> <Mt> <yn> р.
uk|yMMMMEEEE|<Mt> <yn> р. <Et>
uk|yMMMMEEEEd|<Et>, <dn> <Mt> <yn> р.
uk|Gy|<yn> <Gt>
uk|GyMMMd|<dn> <Mt> <yn> р. <Gt>
uk|GyMMMMEEEEd|<Et>, <dn> <Mt> <yn> р. <Gt>
uk|GyMd|<dN>-<MN>-<yn> <Gt>
uk|s|<sn>
uk|sz|<sn> <zt>
uk|szzzz|<sn> <zt>
uk|m|<mn>
uk|mz|<mn> <zt>
uk|mzzzz|<mn> <zt>
uk|ms|<mN>:<sN>
uk|msz|<mN>:<sN> <zt>
uk|mszzzz|<mN>:<sN> <zt>
uk|h|<hn> <at>
uk|hz|<hn> <at> <zt>
uk|hzzzz|<hn> <at> <zt>
uk|hs|<hn> <at> (секунда: <sn>)
uk|hsz|<hn> <at> <zt> (секунда: <sn>)
uk|hszzzz|<hn> <at> <zt> (секунда: <sn>)
uk|hm|<hn>:<mN> <at>
uk|hmz|<hn>:<mN> <at> <zt>
uk|hmzzzz|<hn>:<mN> <at> <zt>
uk|hms|<hn>:<mN>:<sN> <at>
uk|hmsz|<hn>:<mN>:<sN> <at> <zt>
uk|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
uk|H|<hn>
uk|Hz|<hN> год <zt>
uk|Hzzzz|<hN> год <zt>
uk|Hs|<hn> (секунда: <sn>)
uk|Hsz|<hN> год <zt> (секунда: <sn>)
uk|Hszzzz|<hN> год <zt> (секунда: <sn>)
uk|Hm|<hN>:<mN>
uk|Hmz|<hN>:<mN> <zt>
uk|Hmzzzz|<hN>:<mN> <zt>
uk|Hms|<hN>:<mN>:<sN>
uk|Hmsz|<hn>:<mN>:<sN> <zt>
uk|Hmszzzz|<hn>:<mN>:<sN> <zt>
uk|yMMMdh|<dn> <Mt> <yn> р., <hn> <at>
uk|yMMMdhm|<dn> <Mt> <yn> р., <hn>:<mN> <at>
uk|yMMMdHm|<dn> <Mt> <yn> р., <hN>:<mN>
uk|yMMMdhmz|<dn> <Mt> <yn> р., <hn>:<mN> <at> <zt>
uk|yMMMdHms|<dn> <Mt> <yn> р., <hN>:<mN>:<sN>
uk|yMMMdHmzzzz|<dn> <Mt> <yn> р., <hN>:<mN> <zt>
uk|MMMEdh|<Et>, <dn> <Mt>, <hn> <at>
uk|MMMEdhm|<Et>, <dn> <Mt>, <hn>:<mN> <at>
uk|MMMEdHm|<Et>, <dn> <Mt>, <hN>:<mN>
uk|MMMEdhmz|<Et>, <dn> <Mt>, <hn>:<mN> <at> <zt>
uk|MMMEdHms|<Et>, <dn> <Mt>, <hN>:<mN>:<sN>
uk|MMMEdHmzzzz|<Et>, <dn> <Mt>, <hN>:<mN> <zt>
uk|yMdh|<dN>.<MN>.<yn>, <hn> <at>
uk|yMdhm|<dN>.<MN>.<yn>, <hn>:<mN> <at>
uk|yMdHm|<dN>.<MN>.<yn>, <hN>:<mN>
uk|yMdhmz|<dN>.<MN>.<yn>, <hn>:<mN> <at> <zt>
uk|yMdHms|<dN>.<MN>.<yn>, <hN>:<mN>:<sN>
uk|yMdHmzzzz|<dN>.<MN>.<yn>, <hN>:<mN> <zt>
uk|Eh|<Et> <hn> <at>
uk|Ehm|<Et> <hn>:<mN> <at>
uk|EHm|<Et> <hN>:<mN>
uk|Ehmz|<Et>, <hn>:<mN> <at> <zt>
uk|EHms|<Et> <hN>:<mN>:<sN>
uk|EHmzzzz|<Et>, <hN>:<mN> <zt>
uk|dh|<dn>, <hn> <at>
uk|dhm|<dn>, <hn>:<mN> <at>
uk|dHm|<dn>, <hN>:<mN>
uk|dhmz|<dn>, <hn>:<mN> <at> <zt>
uk|dHms|<dn>, <hN>:<mN>:<sN>
uk|dHmzzzz|<dn>, <hN>:<mN> <zt>
uk|MMMMdh|<dn> <Mt> о <hn> <at>
uk|MMMMdhm|<dn> <Mt> о <hn>:<mN> <at>
uk|MMMMdHm|<dn> <Mt> о <hN>:<mN>
uk|MMMMdhmz|<dn> <Mt> о <hn>:<mN> <at> <zt>
uk|MMMMdHms|<dn> <Mt> о <hN>:<mN>:<sN>
uk|MMMMdHmzzzz|<dn> <Mt> о <hN>:<mN> <zt>
uk|yMMMMEEEEdh|<Et>, <dn> <Mt> <yn> р. о <hn> <at>
uk|yMMMMEEEEdhm|<Et>, <dn> <Mt> <yn> р. о <hn>:<mN> <at>
uk|yMMMMEEEEdHm|<Et>, <dn> <Mt> <yn> р. о <hN>:<mN>
uk|yMMMMEEEEdhmz|<Et>, <dn> <Mt> <yn> р. о <hn>:<mN> <at> <zt>
uk|yMMMMEEEEdHms|<Et>, <dn> <Mt> <yn> р. о <hN>:<mN>:<sN>
uk|yMMMMEEEEdHmzzzz|<Et>, <dn> <Mt> <yn> р. о <hN>:<mN> <zt>
uk|EEEEdh|<Et>, <dn>, <hn> <at>
uk|EEEEdhm|<Et>, <dn>, <hn>:<mN> <at>
uk|EEEEdHm|<Et>, <dn>, <hN>:<mN>
uk|EEEEdhmz|<Et>, <dn>, <hn>:<mN> <at> <zt>
uk|EEEEdHms|<Et>, <dn>, <hN>:<mN>:<sN>
uk|EEEEdHmzzzz|<Et>, <dn>, <hN>:<mN> <zt>
uz|d|<dn>
uz|E|<Et>
uz|Ed|<dn>, <Et>
uz|EEEE|<Et>
uz|EEEEd|<dn>, <Et>
uz|M|<MN>
uz|Md|<dN>/<MN>
uz|ME|<MN> <Et>
uz|MEd|<Et>, <dN>/<MN>
uz|MEEEE|<MN> <Et>
uz|MEEEEd|<Et>, <dN>/<MN>
uz|MM|<MN>
uz|MMd|<dN>/<MN>
uz|MME|<Et> (oy: <MN>)
uz|MMEd|<Et>, <dN>/<MN>
uz|MMEEEE|<MN> <Et>
uz|MMEEEEd|<Et>, <dN>/<MN>
uz|MMM|<Mt>
uz|MMMd|<dn>-<Mt>
uz|MMME|<Mt> <Et>
uz|MMMEd|<Et>, <dn>-<Mt>
uz|MMMEEEE|<Mt> <Et>
uz|MMMEEEEd|<Et>, <dn>-<Mt>
uz|MMMM|<Mt>
uz|MMMMd|<dn>-<Mt>
uz|MMMME|<Et> (oy: <Mt>)
uz|MMMMEd|<Et>, <dn>-<Mt>
uz|MMMMEEEE|<Mt> <Et>
uz|MMMMEEEEd|<Et>, <dn>-<Mt>
uz|y|<yn>
uz|yd|<yn> (kun: <dn>)
uz|yE|<yn> <Et>
uz|yEd|<yn> <dn>, <Et>
uz|yEEEE|<yn> <Et>
uz|yEEEEd|<yn> <dn>, <Et>
uz|yM|<MN>.<yn>
uz|yMd|<dN>/<MN>/<yn>
uz|yME|<MN>.<yn> <Et>
uz|yMEd|<Et>, <dN>/<MN>/<yn>
uz|yMEEEE|<MN>.<yn> <Et>
uz|yMEEEEd|<Et>, <dN>/<MN>/<yn>
uz|yMM|<MN>.<yn>
uz|yMMd|<dN>/<MN>/<yn>
uz|yMME|<MN>.<yn> <Et>
uz|yMMEd|<Et>, <dN>/<MN>/<yn>
uz|yMMEEEE|<MN>.<yn> <Et>
uz|yMMEEEEd|<Et>, <dN>/<MN>/<yn>
uz|yMMM|<Mt>, <yn>
uz|yMMMd|<dn>-<Mt>, <yn>
uz|yMMME|<Mt>, <yn> <Et>
uz|yMMMEd|<Et>, <dn>-<Mt>, <yn>
uz|yMMMEEEE|<Mt>, <yn> <Et>
uz|yMMMEEEEd|<Et>, <dn>-<Mt>, <yn>
uz|yMMMM|<Mt>, <yn>
uz|yMMMMd|<dn>-<Mt>, <yn>
uz|yMMMME|<Mt>, <yn> <Et>
uz|yMMMMEd|<Et>, <dn>-<Mt>, <yn>
uz|yMMMMEEEE|<Mt>, <yn> <Et>
uz|yMMMMEEEEd|<Et>, <dn>-<Mt>, <yn>
uz|Gy|<Gt> <yn>
uz|GyMMMd|<dn>-<Mt>, <Gt> <yn>
uz|GyMMMMEEEEd|<Et>, <dn>-<Mt>, <Gt> <yn>
uz|GyMd|<dN>.<MN>.<yn> <Gt>
uz|s|<sn>
uz|sz|<sn> <zt>
uz|szzzz|<sn> <zt>
uz|m|<mn>
uz|mz|<mn> <zt>
uz|mzzzz|<mn> <zt>
uz|ms|<mN>:<sN>
uz|msz|<mN>:<sN> <zt>
uz|mszzzz|<mN>:<sN> <zt>
uz|h|<hn> <at>
uz|hz|<hn> <at> <zt>
uz|hzzzz|<hn> <at> <zt>
uz|hs|<hn> <at> (soniya: <sn>)
uz|hsz|<hn> <at> <zt> (soniya: <sn>)
uz|hszzzz|<hn> <at> <zt> (soniya: <sn>)
uz|hm|<hn>:<mN> <at>
uz|hmz|<hn>:<mN> <at> (<zt>)
uz|hmzzzz|<hn>:<mN> <at> (<zt>)
uz|hms|<hn>:<mN>:<sN> <at>
uz|hmsz|<hn>:<mN>:<sN> <at> (<zt>)
uz|hmszzzz|<hn>:<mN>:<sN> <at> (<zt>)
uz|H|<hN>
uz|Hz|<hN>h <zt>
uz|Hzzzz|<hN>h <zt>
uz|Hs|<hN> (soniya: <sn>)
uz|Hsz|<hN>h <zt> (soniya: <sn>)
uz|Hszzzz|<hN>h <zt> (soniya: <sn>)
uz|Hm|<hN>:<mN>
uz|Hmz|<hN>:<mN> (<zt>)
uz|Hmzzzz|<hN>:<mN> (<zt>)
uz|Hms|<hN>:<mN>:<sN>
uz|Hmsz|<hn>:<mN>:<sN> (<zt>)
uz|Hmszzzz|<hn>:<mN>:<sN> (<zt>)
uz|yMMMdh|<dn>-<Mt>, <yn>, <hn> <at>
uz|yMMMdhm|<dn>-<Mt>, <yn>, <hn>:<mN> <at>
uz|yMMMdHm|<dn>-<Mt>, <yn>, <hN>:<mN>
uz|yMMMdhmz|<dn>-<Mt>, <yn>, <hn>:<mN> <at> (<zt>)
uz|yMMMdHms|<dn>-<Mt>, <yn>, <hN>:<mN>:<sN>
uz|yMMMdHmzzzz|<dn>-<Mt>, <yn>, <hN>:<mN> (<zt>)
uz|MMMEdh|<Et>, <dn>-<Mt>, <hn> <at>
uz|MMMEdhm|<Et>, <dn>-<Mt>, <hn>:<mN> <at>
uz|MMMEdHm|<Et>, <dn>-<Mt>, <hN>:<mN>
uz|MMMEdhmz|<Et>, <dn>-<Mt>, <hn>:<mN> <at> (<zt>)
uz|MMMEdHms|<Et>, <dn>-<Mt>, <hN>:<mN>:<sN>
uz|MMMEdHmzzzz|<Et>, <dn>-<Mt>, <hN>:<mN> (<zt>)
uz|yMdh|<dN>/<MN>/<yn>, <hn> <at>
uz|yMdhm|<dN>/<MN>/<yn>, <hn>:<mN> <at>
uz|yMdHm|<dN>/<MN>/<yn>, <hN>:<mN>
uz|yMdhmz|<dN>/<MN>/<yn>, <hn>:<mN> <at> (<zt>)
uz|yMdHms|<dN>/<MN>/<yn>, <hN>:<mN>:<sN>
uz|yMdHmzzzz|<dN>/<MN>/<yn>, <hN>:<mN> (<zt>)
uz|Eh|<Et>, <hn> <at>
uz|Ehm|<Et>, <hn>:<mN> <at>
uz|EHm|<Et>, <hN>:<mN>
uz|Ehmz|<Et>, <hn>:<mN> <at> (<zt>)
uz|EHms|<Et>, <hN>:<mN>:<sN>
uz|EHmzzzz|<Et>, <hN>:<mN> (<zt>)
uz|dh|<dn>, <hn> <at>
uz|dhm|<dn>, <hn>:<mN> <at>
uz|dHm|<dn>, <hN>:<mN>
uz|dhmz|<dn>, <hn>:<mN> <at> (<zt>)
uz|dHms|<dn>, <hN>:<mN>:<sN>
uz|dHmzzzz|<dn>, <hN>:<mN> (<zt>)
uz|MMMMdh|<dn>-<Mt>, <hn> <at>
uz|MMMMdhm|<dn>-<Mt>, <hn>:<mN> <at>
uz|MMMMdHm|<dn>-<Mt>, <hN>:<mN>
uz|MMMMdhmz|<dn>-<Mt>, <hn>:<mN> <at> (<zt>)
uz|MMMMdHms|<dn>-<Mt>, <hN>:<mN>:<sN>
uz|MMMMdHmzzzz|<dn>-<Mt>, <hN>:<mN> (<zt>)
uz|yMMMMEEEEdh|<Et>, <dn>-<Mt>, <yn>, <hn> <at>
uz|yMMMMEEEEdhm|<Et>, <dn>-<Mt>, <yn>, <hn>:<mN> <at>
uz|yMMMMEEEEdHm|<Et>, <dn>-<Mt>, <yn>, <hN>:<mN>
uz|yMMMMEEEEdhmz|<Et>, <dn>-<Mt>, <yn>, <hn>:<mN> <at> (<zt>)
uz|yMMMMEEEEdHms|<Et>, <dn>-<Mt>, <yn>, <hN>:<mN>:<sN>
uz|yMMMMEEEEdHmzzzz|<Et>, <dn>-<Mt>, <yn>, <hN>:<mN> (<zt>)
uz|EEEEdh|<dn>, <Et>, <hn> <at>
uz|EEEEdhm|<dn>, <Et>, <hn>:<mN> <at>
uz|EEEEdHm|<dn>, <Et>, <hN>:<mN>
uz|EEEEdhmz|<dn>, <Et>, <hn>:<mN> <at> (<zt>)
uz|EEEEdHms|<dn>, <Et>, <hN>:<mN>:<sN>
uz|EEEEdHmzzzz|<dn>, <Et>, <hN>:<mN> (<zt>)
vi|d|<dn>
vi|E|<Et>
vi|Ed|<Et>, ngày <dn>
vi|EEEE|<Et>
vi|EEEEd|<Et>, ngày <dn>
vi|M|<Mn>
vi|Md|<dn>/<Mn>
vi|ME|<Mn> <Et>
vi|MEd|<Et>, <dn>/<Mn>
vi|MEEEE|<Mn> <Et>
vi|MEEEEd|<Et>, <dn>/<Mn>
vi|MM|<MN>
vi|MMd|<dn>-<MN>
vi|MME|<Et> (Tháng: <MN>)
vi|MMEd|<Et>, <dn>/<MN>
vi|MMEEEE|<MN> <Et>
vi|MMEEEEd|<Et>, <dn>/<MN>
vi|MMM|<Mt>
vi|MMMd|<dn> <Mt>
vi|MMME|<Mt> <Et>
vi|MMMEd|<Et>, <dn> <Mt>
vi|MMMEEEE|<Mt> <Et>
vi|MMMEEEEd|<Et>, <dn> <Mt>
vi|MMMM|<Mt>
vi|MMMMd|<dn> <Mt>
vi|MMMME|<Et> (Tháng: <Mt>)
vi|MMMMEd|<Et>, <dn> <Mt>
vi|MMMMEEEE|<Mt> <Et>
vi|MMMMEEEEd|<Et>, <dn> <Mt>
vi|y|<yn>
vi|yd|<yn> (Ngày: <dn>)
vi|yE|<yn> <Et>
vi|yEd|<yn> <Et>, ngày <dn>
vi|yEEEE|<yn> <Et>
vi|yEEEEd|<yn> <Et>, ngày <dn>
vi|yM|<Mn>/<yn>
vi|yMd|<dn>/<Mn>/<yn>
vi|yME|<Mn>/<yn> <Et>
vi|yMEd|<Et>, <dn>/<Mn>/<yn>
vi|yMEEEE|<Mn>/<yn> <Et>
vi|yMEEEEd|<Et>, <dn>/<Mn>/<yn>
vi|yMM|tháng <MN>, <yn>
vi|yMMd|<dn>/<MN>/<yn>
vi|yMME|tháng <MN>, <yn> <Et>
vi|yMMEd|<Et>, <dn>/<MN>/<yn>
vi|yMMEEEE|tháng <MN>, <yn> <Et>
vi|yMMEEEEd|<Et>, <dn>/<MN>/<yn>
vi|yMMM|<Mt> <yn>
vi|yMMMd|<dn> <Mt>, <yn>
vi|yMMME|<Mt> <yn> <Et>
vi|yMMMEd|<Et>, <dn> <Mt>, <yn>
vi|yMMMEEEE|<Mt> <yn> <Et>
vi|yMMMEEEEd|<Et>, <dn> <Mt>, <yn>
vi|yMMMM|<Mt> năm <yn>
vi|yMMMMd|<dn> <Mt>, <yn>
vi|yMMMME|<Mt> năm <yn> <Et>
vi|yMMMMEd|<Et>, <dn> <Mt>, <yn>
vi|yMMMMEEEE|<Mt> năm <yn> <Et>
vi|yMMMMEEEEd|<Et>, <dn> <Mt>, <yn>
vi|Gy|<yn> <Gt>
vi|GyMMMd|<dn> <Mt>, <yn> <Gt>
vi|GyMMMMEEEEd|<Et>, <dn> <Mt>, <yn> <Gt>
vi|GyMd|<dn>/<Mn>/<yn> <Gt>
vi|s|<sn>
vi|sz|<sn> <zt>
vi|szzzz|<sn> <zt>
vi|m|<mn>
vi|mz|<mn> <zt>
vi|mzzzz|<mn> <zt>
vi|ms|<mN>:<sN>
vi|msz|<mN>:<sN> <zt>
vi|mszzzz|<mN>:<sN> <zt>
vi|h|<hn> <at>
vi|hz|<hn> giờ <at> <zt>
vi|hzzzz|<hn> giờ <at> <zt>
vi|hs|<hn> <at> (Giây: <sn>)
vi|hsz|<hn> giờ <at> <zt> (Giây: <sn>)
vi|hszzzz|<hn> giờ <at> <zt> (Giây: <sn>)
vi|hm|<hn>:<mN> <at>
vi|hmz|<hn>:<mN> <at> <zt>
vi|hmzzzz|<hn>:<mN> <at> <zt>
vi|hms|<hn>:<mN>:<sN> <at>
vi|hmsz|<hn>:<mN>:<sN> <at> <zt>
vi|hmszzzz|<hn>:<mN>:<sN> <at> <zt>
vi|H|<hN> giờ
vi|Hz|<hN>h <zt>
vi|Hzzzz|<hN>h <zt>
vi|Hs|<hN> giờ (Giây: <sn>)
vi|Hsz|<hN>h <zt> (Giây: <sn>)
vi|Hszzzz|<hN>h <zt> (Giây: <sn>)
vi|Hm|<hn>:<mN>
vi|Hmz|<hN>:<mN> <zt>
vi|Hmzzzz|<hN>:<mN> <zt>
vi|Hms|<hN>:<mN>:<sN>
vi|Hmsz|<hn>:<mN>:<sN> <zt>
vi|Hmszzzz|<hn>:<mN>:<sN> <zt>
vi|yMMMdh|<hn> <at> <dn> <Mt>, <yn>
vi|yMMMdhm|<hn>:<mN> <at> <dn> <Mt>, <yn>
vi|yMMMdHm|<hn>:<mN> <dn> <Mt>, <yn>
vi|yMMMdhmz|<hn>:<mN> <at> <zt> <dn> <Mt>, <yn>
vi|yMMMdHms|<hN>:<mN>:<sN> <dn> <Mt>, <yn>
vi|yMMMdHmzzzz|<hN>:<mN> <zt> <dn> <Mt>, <yn>
vi|MMMEdh|<hn> <at> <Et>, <dn> <Mt>
vi|MMMEdhm|<hn>:<mN> <at> <Et>, <dn> <Mt>
vi|MMMEdHm|<hn>:<mN> <Et>, <dn> <Mt>
vi|MMMEdhmz|<hn>:<mN> <at> <zt> <Et>, <dn> <Mt>
vi|MMMEdHms|<hN>:<mN>:<sN> <Et>, <dn> <Mt>
vi|MMMEdHmzzzz|<hN>:<mN> <zt> <Et>, <dn> <Mt>
vi|yMdh|<hn> <at> <dn>/<Mn>/<yn>
vi|yMdhm|<hn>:<mN> <at> <dn>/<Mn>/<yn>
vi|yMdHm|<hn>:<mN> <dn>/<Mn>/<yn>
vi|yMdhmz|<hn>:<mN> <at> <zt> <dn>/<Mn>/<yn>
vi|yMdHms|<hN>:<mN>:<sN> <dn>/<Mn>/<yn>
vi|yMdHmzzzz|<hN>:<mN> <zt> <dn>/<Mn>/<yn>
vi|Eh|<hn> giờ <at> <Et>
vi|Ehm|<hn>:<mN> <at> <Et>
vi|EHm|<hN>:<mN> <Et>
vi|Ehmz|<hn>:<mN> <at> <zt> <Et>
vi|EHms|<hN>:<mN>:<sN> <Et>
vi|EHmzzzz|<hN>:<mN> <zt> <Et>
vi|dh|<hn> <at> <dn>
vi|dhm|<hn>:<mN> <at> <dn>
vi|dHm|<hn>:<mN> <dn>
vi|dhmz|<hn>:<mN> <at> <zt> <dn>
vi|dHms|<hN>:<mN>:<sN> <dn>
vi|dHmzzzz|<hN>:<mN> <zt> <dn>
vi|MMMMdh|lúc <hn> <at> <dn> <Mt>
vi|MMMMdhm|lúc <hn>:<mN> <at> <dn> <Mt>
vi|MMMMdHm|lúc <hn>:<mN> <dn> <Mt>
vi|MMMMdhmz|lúc <hn>:<mN> <at> <zt> <dn> <Mt>
vi|MMMMdHms|lúc <hN>:<mN>:<sN> <dn> <Mt>
vi|MMMMdHmzzzz|lúc <hN>:<mN> <zt> <dn> <Mt>
vi|yMMMMEEEEdh|lúc <hn> <at> <Et>, <dn> <Mt>, <yn>
vi|yMMMMEEEEdhm|lúc <hn>:<mN> <at> <Et>, <dn> <Mt>, <yn>
vi|yMMMMEEEEdHm|lúc <hn>:<mN> <Et>, <dn> <Mt>, <yn>
vi|yMMMMEEEEdhmz|lúc <hn>:<mN> <at> <zt> <Et>, <dn> <Mt>, <yn>
vi|yMMMMEEEEdHms|lúc <hN>:<mN>:<sN> <Et>, <dn> <Mt>, <yn>
vi|yMMMMEEEEdHmzzzz|lúc <hN>:<mN> <zt> <Et>, <dn> <Mt>, <yn>
vi|EEEEdh|<hn> <at> <Et>, ngày <dn>
vi|EEEEdhm|<hn>:<mN> <at> <Et>, ngày <dn>
vi|EEEEdHm|<hn>:<mN> <Et>, ngày <dn>
vi|EEEEdhmz|<hn>:<mN> <at> <zt> <Et>, ngày <dn>
vi|EEEEdHms|<hN>:<mN>:<sN> <Et>, ngày <dn>
vi|EEEEdHmzzzz|<hN>:<mN> <zt> <Et>, ngày <dn>
zh|d|<dn>日
zh|E|<Et>
zh|Ed|<dn>日<Et>
zh|EEEE|<Et>
zh|EEEEd|<dn>日<Et>
zh|M|<Mn>月
zh|Md|<Mn>/<dn>
zh|ME|<Mn>月 <Et>
zh|MEd|<Mn>/<dn><Et>
zh|MEEEE|<Mn>月 <Et>
zh|MEEEEd|<Mn>/<dn><Et>
zh|MM|<MN>月
zh|MMd|<MN>/<dn>
zh|MME|<Et> (月: <MN>月)
zh|MMEd|<MN>/<dn><Et>
zh|MMEEEE|<MN>月 <Et>
zh|MMEEEEd|<MN>/<dn><Et>
zh|MMM|<Mt>
zh|MMMd|<Mn>月<dn>日
zh|MMME|<Mt> <Et>
zh|MMMEd|<Mn>月<dn>日<Et>
zh|MMMEEEE|<Mt> <Et>
zh|MMMEEEEd|<Mn>月<dn>日<Et>
zh|MMMM|<Mt>
zh|MMMMd|<Mn>月<dn>日
zh|MMMME|<Et> (月: <Mt>)
zh|MMMMEd|<Mn>月<dn>日<Et>
zh|MMMMEEEE|<Mt> <Et>
zh|MMMMEEEEd|<Mn>月<dn>日<Et>
zh|y|<yn>年
zh|yd|<yn>年 (日: <dn>日)
zh|yE|<yn>年 <Et>
zh|yEd|<yn>年 <dn>日<Et>
zh|yEEEE|<yn>年 <Et>
zh|yEEEEd|<yn>年 <dn>日<Et>
zh|yM|<yn>/<Mn>
zh|yMd|<yn>/<Mn>/<dn>
zh|yME|<yn>/<Mn> <Et>
zh|yMEd|<yn>/<Mn>/<dn><Et>
zh|yMEEEE|<yn>/<Mn> <Et>
zh|yMEEEEd|<yn>年<Mn>月<dn>日<Et>
zh|yMM|<yn>年<Mn>月
zh|yMMd|<yn>/<MN>/<dn>
zh|yMME|<yn>年<Mn>月 <Et>
zh|yMMEd|<yn>/<MN>/<dn><Et>
zh|yMMEEEE|<yn>年<Mn>月 <Et>
zh|yMMEEEEd|<yn>年<MN>月<dn>日<Et>
zh|yMMM|<yn>年<Mn>月
zh|yMMMd|<yn>年<Mn>月<dn>日
zh|yMMME|<yn>年<Mn>月 <Et>
zh|yMMMEd|<yn>年<Mn>月<dn>日<Et>
zh|yMMMEEEE|<yn>年<Mn>月 <Et>
zh|yMMMEEEEd|<yn>年<Mn>月<dn>日<Et>
zh|yMMMM|<yn>年<Mn>月
zh|yMMMMd|<yn>年<Mn>月<dn>日
zh|yMMMME|<yn>年<Mn>月 <Et>
zh|yMMMMEd|<yn>年<Mn>月<dn>日<Et>
zh|yMMMMEEEE|<yn>年<Mn>月 <Et>
zh|yMMMMEEEEd|<yn>年<Mn>月<dn>日<Et>
zh|Gy|<Gt><yn>年
zh|GyMMMd|<Gt><yn>年<Mn>月<dn>日
zh|GyMMMMEEEEd|<Gt><yn>年<Mn>月<dn>日<Et>
zh|GyMd|<Gt><yn>-<MN>-<dN>
zh|s|<sn>
zh|sz|<zt><sn>
zh|szzzz|<zt><sn>
zh|m|<mn>
zh|mz|<zt><mn>
zh|mzzzz|<zt><mn>
zh|ms|<mN>:<sN>
zh|msz|<zt><mN>:<sN>
zh|mszzzz|<zt><mN>:<sN>
zh|h|<at><hn>时
zh|hz|<zt><at><hn>时
zh|hzzzz|<zt><at><hn>时
zh|hs|<at><hn>时 (秒: <sn>)
zh|hsz|<zt><at><hn>时 (秒: <sn>)
zh|hszzzz|<zt><at><hn>时 (秒: <sn>)
zh|hm|<at><hn>:<mN>
zh|hmz|<zt><at><hn>:<mN>
zh|hmzzzz|<zt><at><hn>:<mN>
zh|hms|<at><hn>:<mN>:<sN>
zh|hmsz|<zt><at><hn>:<mN>:<sN>
zh|hmszzzz|<zt><at><hn>:<mN>:<sN>
zh|H|<hn>时
zh|Hz|<zt><hn>时
zh|Hzzzz|<zt><hn>时
zh|Hs|<hn>时 (秒: <sn>)
zh|Hsz|<zt><hn>时 (秒: <sn>)
zh|Hszzzz|<zt><hn>时 (秒: <sn>)
zh|Hm|<hN>:<mN>
zh|Hmz|<zt> <hN>:<mN>
zh|Hmzzzz|<zt> <hN>:<mN>
zh|Hms|<hN>:<mN>:<sN>
zh|Hmsz|<zt> <hn>:<mN>:<sN>
zh|Hmszzzz|<zt> <hn>:<mN>:<sN>
zh|yMMMdh|<yn>年<Mn>月<dn>日 <at><hn>时
zh|yMMMdhm|<yn>年<Mn>月<dn>日 <at><hn>:<mN>
zh|yMMMdHm|<yn>年<Mn>月<dn>日 <hN>:<mN>
zh|yMMMdhmz|<yn>年<Mn>月<dn>日 <zt><at><hn>:<mN>
zh|yMMMdHms|<yn>年<Mn>月<dn>日 <hN>:<mN>:<sN>
zh|yMMMdHmzzzz|<yn>年<Mn>月<dn>日 <zt> <hN>:<mN>
zh|MMMEdh|<Mn>月<dn>日<Et> <at><hn>时
zh|MMMEdhm|<Mn>月<dn>日<Et> <at><hn>:<mN>
zh|MMMEdHm|<Mn>月<dn>日<Et> <hN>:<mN>
zh|MMMEdhmz|<Mn>月<dn>日<Et> <zt><at><hn>:<mN>
zh|MMMEdHms|<Mn>月<dn>日<Et> <hN>:<mN>:<sN>
zh|MMMEdHmzzzz|<Mn>月<dn>日<Et> <zt> <hN>:<mN>
zh|yMdh|<yn>/<Mn>/<dn> <at><hn>时
zh|yMdhm|<yn>/<Mn>/<dn> <at><hn>:<mN>
zh|yMdHm|<yn>/<Mn>/<dn> <hN>:<mN>
zh|yMdhmz|<yn>/<Mn>/<dn> <zt><at><hn>:<mN>
zh|yMdHms|<yn>/<Mn>/<dn> <hN>:<mN>:<sN>
zh|yMdHmzzzz|<yn>/<Mn>/<dn> <zt> <hN>:<mN>
zh|Eh|<Et><at><hn>时
zh|Ehm|<Et><at><hn>:<mN>
zh|EHm|<Et><hN>:<mN>
zh|Ehmz|<Et> <zt><at><hn>:<mN>
zh|EHms|<Et><hN>:<mN>:<sN>
zh|EHmzzzz|<Et> <zt> <hN>:<mN>
zh|dh|<dn>日 <at><hn>时
zh|dhm|<dn>日 <at><hn>:<mN>
zh|dHm|<dn>日 <hN>:<mN>
zh|dhmz|<dn>日 <zt><at><hn>:<mN>
zh|dHms|<dn>日 <hN>:<mN>:<sN>
zh|dHmzzzz|<dn>日 <zt> <hN>:<mN>
zh|MMMMdh|<Mn>月<dn>日 <at><hn>时
zh|MMMMdhm|<Mn>月<dn>日 <at><hn>:<mN>
zh|MMMMdHm|<Mn>月<dn>日 <hN>:<mN>
zh|MMMMdhmz|<Mn>月<dn>日 <zt><at><hn>:<mN>
zh|MMMMdHms|<Mn>月<dn>日 <hN>:<mN>:<sN>
zh|MMMMdHmzzzz|<Mn>月<dn>日 <zt> <hN>:<mN>
zh|yMMMMEEEEdh|<yn>年<Mn>月<dn>日<Et> <at><hn>时
zh|yMMMMEEEEdhm|<yn>年<Mn>月<dn>日<Et> <at><hn>:<mN>
zh|yMMMMEEEEdHm|<yn>年<Mn>月<dn>日<Et> <hN>:<mN>
zh|yMMMMEEEEdhmz|<yn>年<Mn>月<dn>日<Et> <zt><at><hn>:<mN>
zh|yMMMMEEEEdHms|<yn>年<Mn>月<dn>日<Et> <hN>:<mN>:<sN>
zh|yMMMMEEEEdHmzzzz|<yn>年<Mn>月<dn>日<Et> <zt> <hN>:<mN>
zh|EEEEdh|<dn>日<Et> <at><hn>时
zh|EEEEdhm|<dn>日<Et> <at><hn>:<mN>
zh|EEEEdHm|<dn>日<Et> <hN>:<mN>
zh|EEEEdhmz|<dn>日<Et> <zt><at><hn>:<mN>
zh|EEEEdHms|<dn>日<Et> <hN>:<mN>:<sN>
zh|EEEEdHmzzzz|<dn>日<Et> <zt> <hN>:<mN>
"""
