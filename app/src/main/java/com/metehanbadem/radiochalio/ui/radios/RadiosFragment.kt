package com.metehanbadem.radiochalio.ui.radios

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.metehanbadem.radiochalio.R
import com.metehanbadem.radiochalio.data.RadioServiceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RadiosFragment : Fragment() {

    private val radioServiceProvider =
        RadioServiceProvider()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_radios, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val populerRadiosObservable = radioServiceProvider
            .getRadioService()
            .getPopularRadios()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.v("Test", "Success: $it") },
                { Log.v("Test", "Error: $it") })

        val locationRadiosObservable = radioServiceProvider
            .getRadioService()
            .getLocationRadios()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.v("Test", "Success: $it") },
                { Log.v("Test", "Error: $it") })


    }

    companion object {

        fun newInstance() = RadiosFragment()
    }
}