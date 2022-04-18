package com.example.kotlinstudy

class kotlin {

}

val square = {number : Int -> number*number}
val nameAge = {name : String, age : Int ->
"my name is ${name} I'm ${age}"}

fun main(){
    println(square(12))
    println(nameAge("Jo", 24))
}