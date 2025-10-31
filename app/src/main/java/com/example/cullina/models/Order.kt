package com.example.cullina.models

object Order {
    val selectedFoods = mutableListOf<Food>()

    fun addFood(food: Food) {
        selectedFoods.add(food)
    }

    fun removeFood(food: Food) {
        selectedFoods.remove(food)
    }

    fun clearOrder() {
        selectedFoods.clear()
    }

    fun getFoodCount(): Int {
        return selectedFoods.size
    }
}