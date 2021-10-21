package com.coffeemolkin.testfilmes.items

import android.view.View


interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int


}

interface IListRVPresenter : IListPresenter<IItemView> {
    fun setFilter(view: View, position: Int, isCheck: Boolean)
    fun toDescFilm(view: View, position: Int)
}
