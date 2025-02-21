package io.github.aughtone.datetime.format.resources



sealed interface StringItem {
    data class Value(val text: String) : StringItem
    data class Plurals(val items: Map<Type, String>) : StringItem {
        enum class Type {
            Zero,
            One,
            Two,
            Few,
            Many,
            Other;
        }

        fun getString(
            quantity: Int,
        ){

        }
    }
    data class Array(val items: List<String>) : StringItem {
        fun getString(
            index: Int,
        ){

        }
    }
}
