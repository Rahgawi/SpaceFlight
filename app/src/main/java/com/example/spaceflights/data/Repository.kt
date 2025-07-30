package com.example.spaceflights.data.repository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spaceflights.data.model.Launch
import com.example.spaceflights.data.model.LaunchResponse
import com.example.spaceflights.data.remote.SpaceFlightsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class Repository { //Daten aus API und im ViewModel verfügbar machen
    private val _launches = MutableLiveData<List<Launch>>() //veränderbare Liste, speichert die Weltraumstart-Daten
    val launches: LiveData<List<Launch>> get() = _launches

    private val _error = MutableLiveData<String>() //Fehler, wird gespeichert und die Benutzeroberfläche kann auf diesen Fehler reagieren.
    val error: LiveData<String> get() = _error

    suspend fun getLaunches() { //Daten aus der API holen
        try {
            val response = SpaceFlightsApi.retrofitService.getLaunches().results //Abruf Daten aus der externen API
            _launches.postValue(response) //Abruf erfolreich, Abspeicherung in Liste
            Log.e("API Test","${response}")
        } catch (e: Exception) {
            Log.e("API Test", e.message.toString()) //Fehlermeldung wird in _error gespeichert
            _error.postValue(e.message ?: "Unknown error occurred") //.e Ausgabe der Fehlermeldung
        }
    }
}