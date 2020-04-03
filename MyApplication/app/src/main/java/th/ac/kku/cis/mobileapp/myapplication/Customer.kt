package th.ac.kku.cis.mobileapp.myapplication

class customer{
    companion object Factory {
        fun create(): customer = customer()
    }
    var username: String? = null
    var objectId: String? = null
    var password: String? = null
    var emailAddress: String? = null
}


class pice{
    companion object Factory {
        fun create(): pice = pice()
    }
    var pice: String? = null
    var objectIdp: String? = null

}

class buys{
    companion object Factory {
        fun create(): buys = buys()
    }
    var name: String? = null
    var numCard: String? = null
    var objectIdb: String? = null
    var confirmCard: String? = null

}