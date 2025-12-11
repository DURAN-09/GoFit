package mx.edu.utez.gofit.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utez.gofit.controller.AccelerometerManager
import kotlin.math.sqrt

class AccelerometerViewModel(
    private val context: Context
) : ViewModel() {


    var uiState by mutableStateOf(AuthState())
        private set

    private val _sendState = MutableLiveData<SendState>()
    val sendState: LiveData<SendState> = _sendState

    private val _x = MutableLiveData<Float>()
    val x: LiveData<Float> = _x

    private val _y = MutableLiveData<Float>()
    val y: LiveData<Float> = _y

    private val _z = MutableLiveData<Float>()
    val z: LiveData<Float> = _z

    private val _steps = MutableLiveData(0)
    val steps: LiveData<Int> = _steps

    private val _meters = MutableLiveData(0f)
    val meters: LiveData<Float> = _meters

    private var lastFiltered = 0f
    private var filtered = 0f
    private val threshold = 1.2f
    private val alpha = 0.9f

    private val accelerometerManager = AccelerometerManager(context) { vx, vy, vz ->
        _x.postValue(vx)
        _y.postValue(vy)
        _z.postValue(vz)
        processSteps(vx, vy, vz)
    }

    fun start() {
        accelerometerManager.start()
    }

    fun stop() {
        accelerometerManager.stop()
    }

    fun sendSession(steps: Int, started: String, ended: String) {
        viewModelScope.launch {
            _sendState.postValue(SendState(loading = true))

            try {
                _sendState.postValue(SendState(success = true))
            } catch (e: Exception) {
                _sendState.postValue(SendState(error = e.message))
            }
        }
    }



    private fun processSteps(x: Float, y: Float, z: Float) {
        val magnitude = sqrt(x * x + y * y + z * z)
        filtered = alpha * filtered + (1 - alpha) * magnitude
        if (filtered - lastFiltered > threshold) {
            val newSteps = (_steps.value ?: 0) + 1
            _steps.postValue(newSteps)
            _meters.postValue(newSteps * 0.75f)
        }
        lastFiltered = filtered
    }
}


data class SendState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
