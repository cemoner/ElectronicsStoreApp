package com.example.fooddeliveryapp.shared.profile.domain.usecase

import com.example.fooddeliveryapp.shared.profile.data.model.request.UserRequest
import com.example.fooddeliveryapp.shared.profile.domain.model.UserInfo
import com.example.fooddeliveryapp.shared.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository)
{

    suspend fun getUser(userRequest: UserRequest):UserInfo = profileRepository.getUser(userRequest)
}