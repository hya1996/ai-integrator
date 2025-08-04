object Modules {
    object Core {
        val framework = core("framework")
        val datastore = core("datastore")
        val network = core("network")
        val ui = core("ui")
        val user = core("user")
        val im = core("im")
    }

    object Data {
        val platform = data("platform")
        val dialogue = data("dialogue")
    }

    object Feature {
        val platform = feature("platform")
        val dialogue = feature("dialogue")
    }

    private fun core(name: String) = ":core:$name"

    private fun data(name: String) = ":data:$name"

    private fun feature(name: String) = ":feature:$name"
}