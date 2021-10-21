package com.coffeemolkin.testfilmes.adapter


import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.coffeemolkin.testfilmes.ui.FilmsPresenter
import com.coffeemolkin.testfilmes.R

import com.coffeemolkin.testfilmes.databinding.ItemFilmBinding
import com.coffeemolkin.testfilmes.databinding.ItemFilterBinding
import com.coffeemolkin.testfilmes.databinding.ItemTitleBinding
import com.coffeemolkin.testfilmes.images.GlideImageLoader

import com.coffeemolkin.testfilmes.items.IListRVPresenter


private const val TYPE_FILTER = 1
private const val TYPE_FILM = 2
private const val TYPE_TITLE = 3

class RvAdapter(
    private val presenter: IListRVPresenter,
    private val imageLoader: GlideImageLoader
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_FILTER) {

            FilterViewHolder(
                ItemFilterBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )


        } else if (viewType == TYPE_FILM) {
            FilmViewHolder(
                ItemFilmBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )


        } else {
            TitleViewHolder(
                ItemTitleBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_FILM) {
            holder as FilmViewHolder
            presenter.bindView(holder.apply { pos = position })

        } else if (getItemViewType(position) == TYPE_FILTER) {
            holder as FilterViewHolder
            presenter.bindView(holder.apply { pos = position })
        } else {
            holder as TitleViewHolder
            presenter.bindView(holder.apply { pos = position })
        }


    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0 || position == FilmsPresenter.filmTitleCount)
            return TYPE_TITLE
        else if (position in 1 until FilmsPresenter.filmTitleCount)
            return TYPE_FILTER
        else
            return TYPE_FILM
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }


    inner class FilterViewHolder(private val vb: ItemFilterBinding) :
        RecyclerView.ViewHolder(vb.root),
        FilterItemView {
        override fun showFilter(filter: String?, isCheck: Boolean) {
            vb.chipTittleFilter.text = filter
            vb.chipTittleFilter.isChecked = isCheck
        }


        override fun onClick() {
            vb.chipTittleFilter.setOnClickListener {
                vb.chipTittleFilter.isChecked = !vb.chipTittleFilter.isChecked
                presenter.setFilter(itemView, pos, vb.chipTittleFilter.isChecked)

            }
        }

        override var pos: Int = -1


    }

    inner class FilmViewHolder(private val vb: ItemFilmBinding) :
        RecyclerView.ViewHolder(vb.root),
        FilmItemView {

        override fun showPicture(url: String?) {
            if (url != null) {
                imageLoader.loadTo(
                    url,
                    vb.imageFilm
                )

            } else {
                vb.imageFilm.setImageResource(R.drawable.no_image)
            }

        }

        override fun showTitle(title: String?) {
            if (title != null) {
                vb.titleFilm.text = title
            } else {
                vb.titleFilm.text = "Без названия"
            }
        }

        override fun onClick() {
            vb.root.setOnClickListener {
                presenter.toDescFilm(itemView, pos)
            }
        }
        override var pos: Int = -1

    }

    inner class TitleViewHolder(private val vb: ItemTitleBinding) :
        RecyclerView.ViewHolder(vb.root),
        TitleItemView {
        override fun showTitle(title: String?) {
            vb.title.text = title
        }


        override var pos: Int = -1


    }


}

