package com.arysugiarto.mandiritest.util

object Const {


    object NETWORK {
        const val PREFIX = "v2/"
        const val SESSION_ID = "session_id"
        const val EQUALS = "="
        const val APIKEY ="9d22ee2c317f440cb7c5fca60f7e7255"

        const val News = PREFIX + "top-headlines?country=us&category=business&apiKey" + EQUALS + APIKEY

        const val AppleNews = PREFIX + "everything?q=apple&from=2022-12-05&to=2022-12-05&sortBy=popularity&apiKey" + EQUALS + APIKEY


    }

    object File {
        object Location {
            const val basePath = "Mandiri/"
            const val storePath = "Store/"
        }

        object MimeType {
            const val image = "image/jpeg"
        }

        object Image {
            const val defaultFileName = "Mandiri-Image"
        }

        object Pending {
            const val isPending = 1
            const val notPending = 0
        }

    }

    object Paging {
        const val PER_PAGE_SMALL = 10
        const val PER_PAGE_MEDIUM = 25
        const val PER_PAGE_LARGE = 50
        const val BEGIN_PAGE = 1
        const val NOTIFICATION_PAGE = 4
    }

    object PRODUCT {
        const val NOT_AVAILABLE = "N/A"
    }

}