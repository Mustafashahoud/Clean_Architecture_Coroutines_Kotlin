/**
 * Configuration of build modules
 */
object BuildModules {
    const val APP = ":app"
    const val CORE = ":core"

    object Features {
        const val MOVIES = ":features:movies"
    }

    object Commons {
        const val UI = ":commons:ui"
        const val DOMAIN = ":commons:domain"
    }

//    object Libraries {
//        const val TEST_UTILS = ":libraries:test_utils"
//    }
}
