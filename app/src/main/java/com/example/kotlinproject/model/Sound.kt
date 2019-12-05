package com.example.kotlinproject.model

class Sound constructor(assetpath : String?){
    var name : String ?= null
    var soundId : Int ?= null
    var assetPath : String ?= null

    init {
        this.assetPath = assetpath
        val path = this.assetPath!!.split("/").dropLastWhile{ it.isEmpty()}.toTypedArray()
        val fullName : String = path[path.size - 1]
        name = fullName.substring(0,fullName.lastIndexOf('.'))
    }
}