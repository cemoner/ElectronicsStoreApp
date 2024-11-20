package com.example.fooddeliveryapp.features.profile.profile.domain.usecase

import com.example.fooddeliveryapp.features.profile.profile.domain.model.UserInfo
import com.example.fooddeliveryapp.features.profile.profile.domain.repository.UserDataRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val profileRepository: UserDataRepository){

    suspend operator fun invoke(storeName:String, userId:String):Result<UserInfo> = profileRepository.getUser(storeName,userId)
}