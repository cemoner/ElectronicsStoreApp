package com.example.fooddeliveryapp.authentication.login.data.repository

import com.example.fooddeliveryapp.authentication.common.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    private val auth:FirebaseAuth
) {

    suspend fun signUp(email:String,password:String): Resource<String>{

        return try {
            val result = auth.createUserWithEmailAndPassword(email,password).await()
            Resource.Success(
                arrayListOf(
                    result.user?.displayName.orEmpty(),
                    result.user?.email.orEmpty(),
                    result.user?.uid.orEmpty()


                ))
        }catch (e:Exception){
            Resource.Error(e)
        }
    }

    suspend fun signIn(email:String,password:String): Resource<String>{
        return try {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            Resource.Success(
                arrayListOf(
                    result.user?.displayName.orEmpty(),
                    result.user?.email.orEmpty(),
                    result.user?.uid.orEmpty()


                ))
        }catch (e:Exception){
            val errorMessage = when (e) {
                is FirebaseAuthException -> when (e.errorCode) {
                    "ERROR_INVALID_EMAIL" -> "The email address is badly formatted."
                    "ERROR_USER_NOT_FOUND" -> "No user record found for this email."
                    "ERROR_WRONG_PASSWORD" -> "The password is incorrect."
                    else -> "An unknown error occurred."
                }
                else -> e.localizedMessage ?: "An unknown error occurred."
            }
            Resource.Error(Exception(errorMessage))
        }
    }
}