package com.saul223655.activitistates.menu.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saul223655.activitistates.menu.data.model.ProductCreateRequest
import com.saul223655.activitistates.menu.data.model.ProductDTO
import com.saul223655.activitistates.menu.domain.CreateProductUseCase
import com.saul223655.activitistates.menu.domain.GetAllProductsUseCase
import kotlinx.coroutines.launch

class MenuViewModel: ViewModel() {
    private val creteProductUseCase = CreateProductUseCase()
    private val getAllProductUseCase = GetAllProductsUseCase()

    private var _id = MutableLiveData<Number>()
    val id : LiveData<Number> = _id

    private var _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private var _costo = MutableLiveData<Number>()
    val costo : LiveData<Number> = _costo

    private var _cantidad = MutableLiveData<Number>()
    val cantidad : LiveData<Number> = _cantidad

    private var _products = MutableLiveData<List<ProductDTO>>()
    val products : LiveData<List<ProductDTO>> = _products

    private var _success = MutableLiveData<Boolean>(false)
    val success : LiveData<Boolean> = _success

    private var _error = MutableLiveData<String>("")
    val error : LiveData<String> = _error

    fun onChangeId (id : Number) {
        _id.value = id
    }

    fun onChangeName (name : String) {
        _name.value = name
    }
    fun onChangeCosto (costo : Number) {
        _costo.value = costo
    }

    fun onChangeCantidad (cantidad : Number) {
        _cantidad.value = cantidad
    }

    fun onChangeListProduct (products : List<ProductDTO>) {
        _products.value = products
    }

    suspend fun onClick(product: ProductCreateRequest) {

        val result = creteProductUseCase(product)

        result.onSuccess { data ->
            if (data.success) {
                _success.value = true
                _error.value = ""
            } else {
                _error.value = "Error al registrar el producto"
            }
        }.onFailure { exception ->
            _success.value = false
            _error.value = exception.message ?: "Error desconocido"
        }
    }

    suspend fun getAll(){
        viewModelScope.launch {
            val result = getAllProductUseCase()
            result.onSuccess { response ->
                response.data?.let { productList ->
                    _products.value = productList
                    _success.value = true
                    _error.value = ""
                } ?: run {
                    _error.value = "Error al obtener los productos"
                }
            }.onFailure { exception ->
                _error.value = exception.message ?: "Error desconocido"
            }
        }
    }

}