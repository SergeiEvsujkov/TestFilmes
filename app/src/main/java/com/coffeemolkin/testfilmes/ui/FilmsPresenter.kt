package com.coffeemolkin.testfilmes.ui

import android.util.Log
import android.view.View
import com.coffeemolkin.testfilmes.adapter.FilmItemView
import com.coffeemolkin.testfilmes.adapter.FilterItemView
import com.coffeemolkin.testfilmes.adapter.TitleItemView
import com.coffeemolkin.testfilmes.data.*
import com.coffeemolkin.testfilmes.items.IItemView
import com.coffeemolkin.testfilmes.items.IListRVPresenter
import com.coffeemolkin.testfilmes.navigation.AndroidScreens
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.terrakok.cicerone.Router

class FilmsPresenter(
    private val filmsRepo: FilmsRepo,
    private val router: Router,

    ) : MvpPresenter<FilmsView>() {


    inner class RepoListPresenter() : IListRVPresenter {

        var mainList =
            mutableListOf<Pair<Int, Any>>()

        var mainListFilter =
            mutableListOf<Pair<Int, Any>>()

        override fun setFilter(view: View, position: Int, isCheck: Boolean) {


            var filterName = ""


            for (filter in mainListFilter) {
                if (filter.first == 1) {
                    if (isCheck) {
                        filterName = "all"
                        (filter.second as Filter).isCheck = false
                        viewState.updateList()
                    } else {

                        (filter.second as Filter).isCheck = false
                        (mainListFilter[position].second as Filter).isCheck = true
                        filterName = (mainListFilter[position].second as Filter).filter.toString()
                        viewState.updateList()

                    }
                }
            }



            doFilters(filterName)

        }

        private fun doFilters(filterName: String) {
            mainListFilter.clear()
            mainListFilter.addAll(mainList)

            if (filterName != "all") {
                var isFilter = false
                val numberFilter = mutableListOf<Int>()
                for (mainList in 0 until mainListFilter.size) {
                    if (mainListFilter[mainList].first == 2) {
                        val listGenr = (mainListFilter[mainList].second as Film).genres
                        if (listGenr != null) {
                            for (genr in listGenr) {
                                if (genr == filterName) {
                                    isFilter = true
                                }
                            }
                        }

                        if (!isFilter) {
                            numberFilter.add(mainList)

                        }
                        isFilter = false
                    }
                }
                numberFilter.sortDescending()
                for (number in numberFilter) {
                    mainListFilter.removeAt(number)

                }
            }
            viewState.updateList()
        }

        override fun toDescFilm(view: View, position: Int) {
            router.navigateTo(AndroidScreens.DescriptionScreens(mainListFilter[position].second as Film))
        }


        override var itemClickListener: ((IItemView) -> Unit)? = null
        override fun bindView(view: IItemView) {
            if (mainListFilter[view.pos].first == 2) {
                view as FilmItemView
                val film = mainListFilter[view.pos].second as Film
                view.showPicture(film.imageUrl)
                view.showTitle(film.localizedName)
                view.onClick()
            }
            if (mainListFilter[view.pos].first == 1) {
                view as FilterItemView
                val title = (mainListFilter[view.pos].second as Filter).filter
                val isCheck = (mainListFilter[view.pos].second as Filter).isCheck
                view.showFilter(title, isCheck)
                view.onClick()
            }
            if (mainListFilter[view.pos].first == 3) {
                view as TitleItemView
                val title = (mainListFilter[view.pos].second as Title).title
                view.showTitle(title)
            }

        }

        override fun getCount(): Int = mainListFilter.size

    }

    val repoListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
        viewState.init()

        repoListPresenter.itemClickListener = { itemView ->

        }

    }

    private fun loadData() {
        filmsRepo.getFilms().enqueue(object : Callback<Films?> {
            override fun onResponse(call: Call<Films?>?, response: Response<Films?>) {
                val filmsList = response.body()?.films
                val filterList = mutableSetOf<Pair<Int, Filter>>()
                if (filmsList != null) {
                    for (filmIn in filmsList) {
                        try {
                            repoListPresenter.mainList.add(Pair(2, filmIn))
                            for (genr in filmIn.genres!!) {
                                filterList.add(Pair(1, Filter(genr)))

                            }
                            viewState.updateList()
                        } catch (e: Exception) {
                            Log.d("CurrencyList :: ", "Failure")
                        }
                    }


                }
                repoListPresenter.mainList.addAll(0, filterList)
                repoListPresenter.mainList.add(filterList.size, Pair(3, Title("Фильмы")))
                filmTitleCount = filterList.size + 1
                repoListPresenter.mainList.add(0, Pair(3, Title("Жанры")))
                repoListPresenter.mainListFilter.addAll(repoListPresenter.mainList)
            }

            override fun onFailure(call: Call<Films?>?, t: Throwable?) {
                Log.d("FilmList :: ", "Failure")
            }

        })

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    companion object {
        var filmTitleCount: Int = 0
    }
}