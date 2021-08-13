package com.hsicen.migration.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hsicen.migration.data.GardenPlantingRepository
import com.hsicen.migration.data.PlantAndGardenPlantings

class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {
    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
        gardenPlantingRepository.getPlantedGardens()
}
