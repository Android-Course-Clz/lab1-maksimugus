package ru.mchernyaev.lab1

data class Header(
    val coverURL: String,
    val coverDescription: String,
    val avatarURL: String,
    val avatarDescription: String,
    val username: String,
    val subscribersNumber: Int,
    val subscriptionsNumber: Int
)
