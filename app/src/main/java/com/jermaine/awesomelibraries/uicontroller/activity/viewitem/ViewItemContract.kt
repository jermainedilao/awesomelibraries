package com.jermaine.awesomelibraries.uicontroller.activity.viewitem


import com.jermaine.awesomelibraries.base.BaseView

interface ViewItemContract {
    interface ViewItemView : BaseView {
        fun setId(id: String?)

        fun setName(name: String?)

        fun setDescription(description: String?)
    }
}
