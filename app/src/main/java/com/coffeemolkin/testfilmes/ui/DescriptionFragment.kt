package com.coffeemolkin.testfilmes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coffeemolkin.testfilmes.R

import com.coffeemolkin.testfilmes.data.Film

import com.coffeemolkin.testfilmes.databinding.FragmentDescriptionBinding
import com.coffeemolkin.testfilmes.images.GlideImageLoader


private const val FILM = "film"


class DescriptionFragment : Fragment() {

    private var vb: FragmentDescriptionBinding? = null

    private var film: Film? = null

    private val imageLoader = GlideImageLoader()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            film = it.getParcelable(FILM)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDescriptionBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb?.nameFilm?.text = film?.name ?: "Без названия"
        vb?.locNameFilm?.text = film?.localizedName ?: "No title"
        vb?.yearFilm?.text = film?.year?.toString() ?: "Не известно"
        vb?.rackingFilm?.text = film?.rating?.toString() ?: "Не известно"
        vb?.description?.text = film?.description ?: "Без описания>"
        showPicture(film!!.imageUrl)

    }

    private fun showPicture(url: String?) {
        if (url != null) {
            vb?.let {
                imageLoader.loadTo(
                    url,
                    it.imageFilm
                )
            }

        } else {
            vb?.imageFilm?.setImageResource(R.drawable.no_image)
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(film: Film) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(FILM, film)
                }
            }
    }
}