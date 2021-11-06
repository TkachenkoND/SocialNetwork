package com.example.hw_3.model

import androidx.room.Entity
import com.example.hw_3.view_model.UserViewModel


class UserData {

    val userList = listOf(

        User(
            0, "Julia", "5 min ago",
            "https://camealabs.org/media/k2/items/cache/3cb06e4cb464be7a87ae9907c7d62b4b_L.jpg",
            "Consequat diam sapien imperdiet fusce nunc dis ipsum bibendum porttitor donec at",
            10, 122, 390, "2K", "12K", 3
        ),

        User(
            1, "Jacob", "11 min ago", "",
            "Fames lacus ultrices luctus commodo pulvinar cras litora curabitur non scelerisque aliquet",
            5, 1566, 2500, "0.125K", "1K", 12
        ),

        User(
            2, "Agata", "3 hours ago",
            "https://www.newamerica.org/images/_feJ6K9mxEruWvCg-8X9cS876U8=/21016/fill-200x200/Justine_van_der_Leun-Emerson_Fellow.jpg",
            "Lorem ipsum dolor sit amet consectetur adipiscing elit tempor felis at, natoque a lacus",
            4, 431, 1299, "13K", "5K", 3
        ),

        User(
            3, "Ida", "Yesterday",
            "https://www.newamerica.org/images/JJVNo1kzAoEh7iAleQan40-ufKk=/21015/fill-200x200/Anna_Louie_Sussman_-_National_Fellow.jpg",
            "Hac inceptos gravida ultricies a habitasse donec consectetur, natoque hendrerit ridiculus",
            6, 1135, 2331, "2.3K", "3.1K", 8
        ),

        User(
            4, "William", "45 min ago",
            "https://www.newamerica.org/images/LNWk8pjzqaESxnSt1l_IbX9Z6po=/21011/fill-200x200/Abrahm_Lustgarten-Emerson_Fellow.jpg",
            "Tincidunt consequat class sapien faucibus ipsum vehicula habitasse, non et risus euismod",
            23, 288, 32, "0.3K", "0.9K", 0
        ),

        User(
            5, "Harry", "Yesterday",
            "https://www.newamerica.org/images/gC03QY2hgO_JZJIfbYDbZvWsh1Q=/21006/fill-200x200/Benoit_Denizet-Lewis_-_National_Fellow.jpg",
            "Lorem ipsum dolor sit amet consectetur adipiscing, elit suspendisse etiam phasellus et integer",
            11, 1001, 45, "1.1K", "4.1K", 15
        ),

        User(
            6, "Angelina", "2 hours ago",
            "https://www.newamerica.org/images/dJPrOUJ9DQ0BjEkh89dKt7JScnI=/21465/fill-200x200/other_headshot_Sara_Hendren.jpeg",
            "Aenean et dui massa fusce ex dolor, eleifend urna morbi vulputate ac, aliquet aliquam",
            9, 14, 55, "6.3K", "1.9K", 1
        )

    )

}