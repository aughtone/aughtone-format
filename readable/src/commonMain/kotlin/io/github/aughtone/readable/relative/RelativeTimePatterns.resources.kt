package io.github.aughtone.readable.relative

import io.github.aughtone.readable.PluralCategory

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR 48.0 by tools/cldr/relative-time.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Whole relative-time phrases for one language, as Unicode CLDR publishes them: one pattern for
 * each unit, direction and plural form, with `{0}` where the count goes.
 *
 * @property categories The plural forms the language distinguishes for whole numbers, in the
 *   order the pattern lists use.
 * @property past Patterns for a time in the past, by unit (second, minute, hour, day, week,
 *   month, year) and then by plural form in [categories] order.
 * @property future The same for a time in the future.
 */
internal class RelativeTimePatterns(
    val categories: List<PluralCategory>,
    val past: List<List<String>>,
    val future: List<List<String>>,
)

/**
 * The languages whose relative-time phrases cannot be built from one template and one unit word
 * per plural form — the unit takes a different grammatical case after "ago" than after "in", or
 * the phrase changes shape altogether — keyed by language code. Every other bundled language is
 * described compactly in `RelativeTime.resources.kt`.
 */
internal val cldrRelativeTimePatterns: Map<String, RelativeTimePatterns> = mapOf(
    "ar" to RelativeTimePatterns(
        categories = listOf(PluralCategory.Zero, PluralCategory.One, PluralCategory.Two, PluralCategory.Few, PluralCategory.Many, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("قبل {0} ثانية", "قبل ثانية واحدة", "قبل ثانيتين", "قبل {0} ثوانِ", "قبل {0} ثانية", "قبل {0} ثانية"),
            /* minute */ listOf("قبل {0} دقيقة", "قبل دقيقة واحدة", "قبل دقيقتين", "قبل {0} دقائق", "قبل {0} دقيقة", "قبل {0} دقيقة"),
            /* hour   */ listOf("قبل {0} ساعة", "قبل ساعة واحدة", "قبل ساعتين", "قبل {0} ساعات", "قبل {0} ساعة", "قبل {0} ساعة"),
            /* day    */ listOf("قبل {0} يوم", "قبل يوم واحد", "قبل يومين", "قبل {0} أيام", "قبل {0} يومًا", "قبل {0} يوم"),
            /* week   */ listOf("قبل {0} أسبوع", "قبل أسبوع واحد", "قبل أسبوعين", "قبل {0} أسابيع", "قبل {0} أسبوعًا", "قبل {0} أسبوع"),
            /* month  */ listOf("قبل {0} شهر", "قبل شهر واحد", "قبل شهرين", "قبل {0} أشهر", "قبل {0} شهرًا", "قبل {0} شهر"),
            /* year   */ listOf("قبل {0} سنة", "قبل سنة واحدة", "قبل سنتين", "قبل {0} سنوات", "قبل {0} سنة", "قبل {0} سنة"),
        ),
        future = listOf(
            /* second */ listOf("خلال {0} ثانية", "خلال ثانية واحدة", "خلال ثانيتين", "خلال {0} ثوانٍ", "خلال {0} ثانية", "خلال {0} ثانية"),
            /* minute */ listOf("خلال {0} دقيقة", "خلال دقيقة واحدة", "خلال دقيقتين", "خلال {0} دقائق", "خلال {0} دقيقة", "خلال {0} دقيقة"),
            /* hour   */ listOf("خلال {0} ساعة", "خلال ساعة واحدة", "خلال ساعتين", "خلال {0} ساعات", "خلال {0} ساعة", "خلال {0} ساعة"),
            /* day    */ listOf("خلال {0} يوم", "خلال يوم واحد", "خلال يومين", "خلال {0} أيام", "خلال {0} يومًا", "خلال {0} يوم"),
            /* week   */ listOf("خلال {0} أسبوع", "خلال أسبوع واحد", "خلال أسبوعين", "خلال {0} أسابيع", "خلال {0} أسبوعًا", "خلال {0} أسبوع"),
            /* month  */ listOf("خلال {0} شهر", "خلال شهر واحد", "خلال شهرين", "خلال {0} أشهر", "خلال {0} شهرًا", "خلال {0} شهر"),
            /* year   */ listOf("خلال {0} سنة", "خلال سنة واحدة", "خلال سنتين", "خلال {0} سنوات", "خلال {0} سنة", "خلال {0} سنة"),
        ),
    ),
    "cs" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Few, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("před {0} sekundou", "před {0} sekundami", "před {0} sekundami"),
            /* minute */ listOf("před {0} minutou", "před {0} minutami", "před {0} minutami"),
            /* hour   */ listOf("před {0} hodinou", "před {0} hodinami", "před {0} hodinami"),
            /* day    */ listOf("před {0} dnem", "před {0} dny", "před {0} dny"),
            /* week   */ listOf("před {0} týdnem", "před {0} týdny", "před {0} týdny"),
            /* month  */ listOf("před {0} měsícem", "před {0} měsíci", "před {0} měsíci"),
            /* year   */ listOf("před {0} rokem", "před {0} lety", "před {0} lety"),
        ),
        future = listOf(
            /* second */ listOf("za {0} sekundu", "za {0} sekundy", "za {0} sekund"),
            /* minute */ listOf("za {0} minutu", "za {0} minuty", "za {0} minut"),
            /* hour   */ listOf("za {0} hodinu", "za {0} hodiny", "za {0} hodin"),
            /* day    */ listOf("za {0} den", "za {0} dny", "za {0} dní"),
            /* week   */ listOf("za {0} týden", "za {0} týdny", "za {0} týdnů"),
            /* month  */ listOf("za {0} měsíc", "za {0} měsíce", "za {0} měsíců"),
            /* year   */ listOf("za {0} rok", "za {0} roky", "za {0} let"),
        ),
    ),
    "fi" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("{0} sekunti sitten", "{0} sekuntia sitten"),
            /* minute */ listOf("{0} minuutti sitten", "{0} minuuttia sitten"),
            /* hour   */ listOf("{0} tunti sitten", "{0} tuntia sitten"),
            /* day    */ listOf("{0} päivä sitten", "{0} päivää sitten"),
            /* week   */ listOf("{0} viikko sitten", "{0} viikkoa sitten"),
            /* month  */ listOf("{0} kuukausi sitten", "{0} kuukautta sitten"),
            /* year   */ listOf("{0} vuosi sitten", "{0} vuotta sitten"),
        ),
        future = listOf(
            /* second */ listOf("{0} sekunnin päästä", "{0} sekunnin päästä"),
            /* minute */ listOf("{0} minuutin päästä", "{0} minuutin päästä"),
            /* hour   */ listOf("{0} tunnin päästä", "{0} tunnin päästä"),
            /* day    */ listOf("{0} päivän päästä", "{0} päivän päästä"),
            /* week   */ listOf("{0} viikon päästä", "{0} viikon päästä"),
            /* month  */ listOf("{0} kuukauden päästä", "{0} kuukauden päästä"),
            /* year   */ listOf("{0} vuoden päästä", "{0} vuoden päästä"),
        ),
    ),
    "hu" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("{0} másodperccel ezelőtt", "{0} másodperccel ezelőtt"),
            /* minute */ listOf("{0} perccel ezelőtt", "{0} perccel ezelőtt"),
            /* hour   */ listOf("{0} órával ezelőtt", "{0} órával ezelőtt"),
            /* day    */ listOf("{0} nappal ezelőtt", "{0} nappal ezelőtt"),
            /* week   */ listOf("{0} héttel ezelőtt", "{0} héttel ezelőtt"),
            /* month  */ listOf("{0} hónappal ezelőtt", "{0} hónappal ezelőtt"),
            /* year   */ listOf("{0} évvel ezelőtt", "{0} évvel ezelőtt"),
        ),
        future = listOf(
            /* second */ listOf("{0} másodperc múlva", "{0} másodperc múlva"),
            /* minute */ listOf("{0} perc múlva", "{0} perc múlva"),
            /* hour   */ listOf("{0} óra múlva", "{0} óra múlva"),
            /* day    */ listOf("{0} nap múlva", "{0} nap múlva"),
            /* week   */ listOf("{0} hét múlva", "{0} hét múlva"),
            /* month  */ listOf("{0} hónap múlva", "{0} hónap múlva"),
            /* year   */ listOf("{0} év múlva", "{0} év múlva"),
        ),
    ),
    "hy" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("{0} վայրկյան առաջ", "{0} վայրկյան առաջ"),
            /* minute */ listOf("{0} րոպե առաջ", "{0} րոպե առաջ"),
            /* hour   */ listOf("{0} ժամ առաջ", "{0} ժամ առաջ"),
            /* day    */ listOf("{0} օր առաջ", "{0} օր առաջ"),
            /* week   */ listOf("{0} շաբաթ առաջ", "{0} շաբաթ առաջ"),
            /* month  */ listOf("{0} ամիս առաջ", "{0} ամիս առաջ"),
            /* year   */ listOf("{0} տարի առաջ", "{0} տարի առաջ"),
        ),
        future = listOf(
            /* second */ listOf("{0} վայրկյանից", "{0} վայրկյանից"),
            /* minute */ listOf("{0} րոպեից", "{0} րոպեից"),
            /* hour   */ listOf("{0} ժամից", "{0} ժամից"),
            /* day    */ listOf("{0} օրից", "{0} օրից"),
            /* week   */ listOf("{0} շաբաթից", "{0} շաբաթից"),
            /* month  */ listOf("{0} ամսից", "{0} ամսից"),
            /* year   */ listOf("{0} տարուց", "{0} տարուց"),
        ),
    ),
    "is" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("fyrir {0} sekúndu", "fyrir {0} sekúndum"),
            /* minute */ listOf("fyrir {0} mínútu", "fyrir {0} mínútum"),
            /* hour   */ listOf("fyrir {0} klukkustund", "fyrir {0} klukkustundum"),
            /* day    */ listOf("fyrir {0} degi", "fyrir {0} dögum"),
            /* week   */ listOf("fyrir {0} viku", "fyrir {0} vikum"),
            /* month  */ listOf("fyrir {0} mánuði", "fyrir {0} mánuðum"),
            /* year   */ listOf("fyrir {0} ári", "fyrir {0} árum"),
        ),
        future = listOf(
            /* second */ listOf("eftir {0} sekúndu", "eftir {0} sekúndur"),
            /* minute */ listOf("eftir {0} mínútu", "eftir {0} mínútur"),
            /* hour   */ listOf("eftir {0} klukkustund", "eftir {0} klukkustundir"),
            /* day    */ listOf("eftir {0} dag", "eftir {0} daga"),
            /* week   */ listOf("eftir {0} viku", "eftir {0} vikur"),
            /* month  */ listOf("eftir {0} mánuð", "eftir {0} mánuði"),
            /* year   */ listOf("eftir {0} ár", "eftir {0} ár"),
        ),
    ),
    "ka" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("{0} წამის წინ", "{0} წამის წინ"),
            /* minute */ listOf("{0} წუთის წინ", "{0} წუთის წინ"),
            /* hour   */ listOf("{0} საათის წინ", "{0} საათის წინ"),
            /* day    */ listOf("{0} დღის წინ", "{0} დღის წინ"),
            /* week   */ listOf("{0} კვირის წინ", "{0} კვირის წინ"),
            /* month  */ listOf("{0} თვის წინ", "{0} თვის წინ"),
            /* year   */ listOf("{0} წლის წინ", "{0} წლის წინ"),
        ),
        future = listOf(
            /* second */ listOf("{0} წამში", "{0} წამში"),
            /* minute */ listOf("{0} წუთში", "{0} წუთში"),
            /* hour   */ listOf("{0} საათში", "{0} საათში"),
            /* day    */ listOf("{0} დღეში", "{0} დღეში"),
            /* week   */ listOf("{0} კვირაში", "{0} კვირაში"),
            /* month  */ listOf("{0} თვეში", "{0} თვეში"),
            /* year   */ listOf("{0} წელიწადში", "{0} წელიწადში"),
        ),
    ),
    "kk" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("{0} секунд бұрын", "{0} секунд бұрын"),
            /* minute */ listOf("{0} минут бұрын", "{0} минут бұрын"),
            /* hour   */ listOf("{0} сағат бұрын", "{0} сағат бұрын"),
            /* day    */ listOf("{0} күн бұрын", "{0} күн бұрын"),
            /* week   */ listOf("{0} апта бұрын", "{0} апта бұрын"),
            /* month  */ listOf("{0} ай бұрын", "{0} ай бұрын"),
            /* year   */ listOf("{0} жыл бұрын", "{0} жыл бұрын"),
        ),
        future = listOf(
            /* second */ listOf("{0} секундтан кейін", "{0} секундтан кейін"),
            /* minute */ listOf("{0} минуттан кейін", "{0} минуттан кейін"),
            /* hour   */ listOf("{0} сағаттан кейін", "{0} сағаттан кейін"),
            /* day    */ listOf("{0} күннен кейін", "{0} күннен кейін"),
            /* week   */ listOf("{0} аптадан кейін", "{0} аптадан кейін"),
            /* month  */ listOf("{0} айдан кейін", "{0} айдан кейін"),
            /* year   */ listOf("{0} жылдан кейін", "{0} жылдан кейін"),
        ),
    ),
    "lt" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Few, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("prieš {0} sekundę", "prieš {0} sekundes", "prieš {0} sekundžių"),
            /* minute */ listOf("prieš {0} minutę", "prieš {0} minutes", "prieš {0} minučių"),
            /* hour   */ listOf("prieš {0} valandą", "prieš {0} valandas", "prieš {0} valandų"),
            /* day    */ listOf("prieš {0} dieną", "prieš {0} dienas", "prieš {0} dienų"),
            /* week   */ listOf("prieš {0} savaitę", "prieš {0} savaites", "prieš {0} savaičių"),
            /* month  */ listOf("prieš {0} mėnesį", "prieš {0} mėnesius", "prieš {0} mėnesių"),
            /* year   */ listOf("prieš {0} metus", "prieš {0} metus", "prieš {0} metų"),
        ),
        future = listOf(
            /* second */ listOf("po {0} sekundės", "po {0} sekundžių", "po {0} sekundžių"),
            /* minute */ listOf("po {0} minutės", "po {0} minučių", "po {0} minučių"),
            /* hour   */ listOf("po {0} valandos", "po {0} valandų", "po {0} valandų"),
            /* day    */ listOf("po {0} dienos", "po {0} dienų", "po {0} dienų"),
            /* week   */ listOf("po {0} savaitės", "po {0} savaičių", "po {0} savaičių"),
            /* month  */ listOf("po {0} mėnesio", "po {0} mėnesių", "po {0} mėnesių"),
            /* year   */ listOf("po {0} metų", "po {0} metų", "po {0} metų"),
        ),
    ),
    "sk" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Few, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("pred {0} sekundou", "pred {0} sekundami", "pred {0} sekundami"),
            /* minute */ listOf("pred {0} minútou", "pred {0} minútami", "pred {0} minútami"),
            /* hour   */ listOf("pred {0} hodinou", "pred {0} hodinami", "pred {0} hodinami"),
            /* day    */ listOf("pred {0} dňom", "pred {0} dňami", "pred {0} dňami"),
            /* week   */ listOf("pred {0} týždňom", "pred {0} týždňami", "pred {0} týždňami"),
            /* month  */ listOf("pred {0} mesiacom", "pred {0} mesiacmi", "pred {0} mesiacmi"),
            /* year   */ listOf("pred {0} rokom", "pred {0} rokmi", "pred {0} rokmi"),
        ),
        future = listOf(
            /* second */ listOf("o {0} sekundu", "o {0} sekundy", "o {0} sekúnd"),
            /* minute */ listOf("o {0} minútu", "o {0} minúty", "o {0} minút"),
            /* hour   */ listOf("o {0} hodinu", "o {0} hodiny", "o {0} hodín"),
            /* day    */ listOf("o {0} deň", "o {0} dni", "o {0} dní"),
            /* week   */ listOf("o {0} týždeň", "o {0} týždne", "o {0} týždňov"),
            /* month  */ listOf("o {0} mesiac", "o {0} mesiace", "o {0} mesiacov"),
            /* year   */ listOf("o {0} rok", "o {0} roky", "o {0} rokov"),
        ),
    ),
    "sl" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Two, PluralCategory.Few, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("pred {0} sekundo", "pred {0} sekundama", "pred {0} sekundami", "pred {0} sekundami"),
            /* minute */ listOf("pred {0} minuto", "pred {0} minutama", "pred {0} minutami", "pred {0} minutami"),
            /* hour   */ listOf("pred {0} uro", "pred {0} urama", "pred {0} urami", "pred {0} urami"),
            /* day    */ listOf("pred {0} dnevom", "pred {0} dnevoma", "pred {0} dnevi", "pred {0} dnevi"),
            /* week   */ listOf("pred {0} tednom", "pred {0} tednoma", "pred {0} tedni", "pred {0} tedni"),
            /* month  */ listOf("pred {0} mesecem", "pred {0} mesecema", "pred {0} meseci", "pred {0} meseci"),
            /* year   */ listOf("pred {0} letom", "pred {0} letoma", "pred {0} leti", "pred {0} leti"),
        ),
        future = listOf(
            /* second */ listOf("čez {0} sekundo", "čez {0} sekundi", "čez {0} sekunde", "čez {0} sekund"),
            /* minute */ listOf("čez {0} minuto", "čez {0} minuti", "čez {0} minute", "čez {0} minut"),
            /* hour   */ listOf("čez {0} uro", "čez {0} uri", "čez {0} ure", "čez {0} ur"),
            /* day    */ listOf("čez {0} dan", "čez {0} dneva", "čez {0} dni", "čez {0} dni"),
            /* week   */ listOf("čez {0} teden", "čez {0} tedna", "čez {0} tedne", "čez {0} tednov"),
            /* month  */ listOf("čez {0} mesec", "čez {0} meseca", "čez {0} mesece", "čez {0} mesecev"),
            /* year   */ listOf("čez {0} leto", "čez {0} leti", "čez {0} leta", "čez {0} let"),
        ),
    ),
    "sq" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("{0} sekondë më parë", "{0} sekonda më parë"),
            /* minute */ listOf("{0} minutë më parë", "{0} minuta më parë"),
            /* hour   */ listOf("{0} orë më parë", "{0} orë më parë"),
            /* day    */ listOf("{0} ditë më parë", "{0} ditë më parë"),
            /* week   */ listOf("{0} javë më parë", "{0} javë më parë"),
            /* month  */ listOf("{0} muaj më parë", "{0} muaj më parë"),
            /* year   */ listOf("{0} vit më parë", "{0} vjet më parë"),
        ),
        future = listOf(
            /* second */ listOf("pas {0} sekonde", "pas {0} sekondash"),
            /* minute */ listOf("pas {0} minute", "pas {0} minutash"),
            /* hour   */ listOf("pas {0} ore", "pas {0} orësh"),
            /* day    */ listOf("pas {0} dite", "pas {0} ditësh"),
            /* week   */ listOf("pas {0} jave", "pas {0} javësh"),
            /* month  */ listOf("pas {0} muaji", "pas {0} muajsh"),
            /* year   */ listOf("pas {0} viti", "pas {0} vjetësh"),
        ),
    ),
    "sr" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Few, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("пре {0} секунде", "пре {0} секунде", "пре {0} секунди"),
            /* minute */ listOf("пре {0} минута", "пре {0} минута", "пре {0} минута"),
            /* hour   */ listOf("пре {0} сата", "пре {0} сата", "пре {0} сати"),
            /* day    */ listOf("пре {0} дана", "пре {0} дана", "пре {0} дана"),
            /* week   */ listOf("пре {0} недеље", "пре {0} недеље", "пре {0} недеља"),
            /* month  */ listOf("пре {0} месеца", "пре {0} месеца", "пре {0} месеци"),
            /* year   */ listOf("пре {0} године", "пре {0} године", "пре {0} година"),
        ),
        future = listOf(
            /* second */ listOf("за {0} секунду", "за {0} секунде", "за {0} секунди"),
            /* minute */ listOf("за {0} минут", "за {0} минута", "за {0} минута"),
            /* hour   */ listOf("за {0} сат", "за {0} сата", "за {0} сати"),
            /* day    */ listOf("за {0} дан", "за {0} дана", "за {0} дана"),
            /* week   */ listOf("за {0} недељу", "за {0} недеље", "за {0} недеља"),
            /* month  */ listOf("за {0} месец", "за {0} месеца", "за {0} месеци"),
            /* year   */ listOf("за {0} годину", "за {0} године", "за {0} година"),
        ),
    ),
    "sw" to RelativeTimePatterns(
        categories = listOf(PluralCategory.One, PluralCategory.Other),
        past = listOf(
            /* second */ listOf("sekunde {0} iliyopita", "sekunde {0} zilizopita"),
            /* minute */ listOf("dakika {0} iliyopita", "dakika {0} zilizopita"),
            /* hour   */ listOf("saa {0} iliyopita", "saa {0} zilizopita"),
            /* day    */ listOf("siku {0} iliyopita", "siku {0} zilizopita"),
            /* week   */ listOf("wiki {0} iliyopita", "wiki {0} zilizopita"),
            /* month  */ listOf("mwezi {0} uliopita", "miezi {0} iliyopita"),
            /* year   */ listOf("mwaka {0} uliopita", "miaka {0} iliyopita"),
        ),
        future = listOf(
            /* second */ listOf("baada ya sekunde {0}", "baada ya sekunde {0}"),
            /* minute */ listOf("baada ya dakika {0}", "baada ya dakika {0}"),
            /* hour   */ listOf("baada ya saa {0}", "baada ya saa {0}"),
            /* day    */ listOf("baada ya siku {0}", "baada ya siku {0}"),
            /* week   */ listOf("baada ya wiki {0}", "baada ya wiki {0}"),
            /* month  */ listOf("baada ya mwezi {0}", "baada ya miezi {0}"),
            /* year   */ listOf("baada ya mwaka {0}", "baada ya miaka {0}"),
        ),
    ),
)
