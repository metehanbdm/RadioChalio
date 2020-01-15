package com.metehanbadem.radiochalio.ui.radios

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.metehanbadem.radiochalio.R
import com.metehanbadem.radiochalio.data.RadioDataSource
import com.metehanbadem.radiochalio.data.remote.RadioServiceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RadiosFragment : Fragment() {

    val radioDataSource = RadioDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_radios, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val a = radioDataSource.fetchPopulerRadios()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
                Log.v("Test", resource.status.toString())
            }

        val b = radioDataSource.fetchLocationRadios()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
                Log.v("Test", resource.status.toString())
            }
    }

    companion object {

        fun newInstance() = RadiosFragment()
    }
}