package com.ubaya.onlineshop

data class Order(val items_iditem:Int, val users_iduser:Int, val tanggalorder:String, val quantity:Int, val subtotal:Int, val gambarorder:String) {
}