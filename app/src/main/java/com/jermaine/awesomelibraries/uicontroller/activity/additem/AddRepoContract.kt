package com.jermaine.awesomelibraries.uicontroller.activity.additem


import com.jermaine.awesomelibraries.base.BaseView

interface AddRepoContract {
    interface AddRepoView : BaseView {
        val id: String

        val name: String

        val description: String

        fun setIdError()

        fun setNameError()

        fun setDescriptionError()

        fun finishScreen()
    }
}
