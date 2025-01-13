package com.example.marsphotos


sealed class MarsPhotoIntent{
    data object LoadMarsIntent : MarsPhotoIntent()
}