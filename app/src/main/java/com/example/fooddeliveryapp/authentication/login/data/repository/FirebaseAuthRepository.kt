package com.example.fooddeliveryapp.authentication.login.data.repository

import com.example.fooddeliveryapp.authentication.common.Resource
import com.google.firebase.auth.FirebaseAuth
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
            Resource.Error(e)
        }
    }

    fun isUserLoggedIn():Boolean = auth.currentUser != null
}