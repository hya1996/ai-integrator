object Modules {
    object Core {
        val framework = core("framework")
        val network = core("network")
        val ui = core("ui")
    }

    object Feature {
        val dialogue = feature("dialogue")
    }

    object Data {
        val dialogue = data("dialogue")
    }

    private fun core(name: String) = ":core:$name"

    private fun feature(name: String) = ":feature:$name"

    private fun data(name: String) = ":data:$name"
}