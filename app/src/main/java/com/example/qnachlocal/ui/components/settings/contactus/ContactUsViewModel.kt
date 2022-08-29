package com.example.qnachlocal.ui.components.settings.contactus

//@HiltViewModel
//class ContactUsViewModel @Inject constructor(
//    private val dataRepository: DataRepository,
//    private val networkHelper: NetworkHelper
//) : BaseViewModel() {
//
//    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
//    val userLoginLiveDataPrivate = LiveEvent<Resource<ResetPasswordResponse>>()
//    val userLoginLiveData: LiveData<Resource<ResetPasswordResponse>> get() = userLoginLiveDataPrivate
//
//    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
//    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
//    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate
//
//    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
//    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
//    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate
//
//
//    fun resetPassword(loginRequest: ResetPasswordRequest) {
//        viewModelScope.launch {
//            userLoginLiveDataPrivate.value = Resource.Loading()
//            if (networkHelper.isNetworkConnected()) {
//                wrapEspressoIdlingResource {
//                    dataRepository.doRemoteResetPassword(loginRequest).collect {
//                        userLoginLiveDataPrivate.value = it
//                    }
//                }
//
//            } //else userLoginLiveDataPrivate.postValue(Resource.DataError(NO_INTERNET_CONNECTION))
//        }
//    }
//
//    fun showToastMessage(errorCode: Int) {
//        val error = errorManager.getError(errorCode)
//        showToastPrivate.value = SingleEvent(error.description)
//    }
//}