package com.metehanbadem.radiochalio.data

import com.metehanbadem.radiochalio.data.remote.Radio
import com.metehanbadem.radiochalio.data.remote.RadioServiceProvider
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.net.ResponseCache

class RadioDataSource {
    private val radioServiceProvider = RadioServiceProvider()

    fun fetchPopulerRadios(): Observable<Resource<List<Radio>>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())

            radioServiceProvider
                .getRadioService()
                .getPopularRadios()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { radioList ->
                        emitter.onNext(Resource.success(radioList))
                        emitter.onComplete()
                    },
                    { error ->
                        emitter.onNext(Resource.error(error.message ?: ""))
                        emitter.onComplete()
                    })
        }

    }

    fun fetchLocationRadios(): Observable<Resource<List<Radio>>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())

            radioServiceProvider
                .getRadioService()
                .getLocationRadios()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { radioList ->
                        emitter.onNext(Resource.success(radioList))
                        emitter.onComplete()
                    },
                    {
                        emitter.onNext(Resource.error(it.message ?: ""))
                        emitter.onComplete()
                    }
                )
        }

    }
}