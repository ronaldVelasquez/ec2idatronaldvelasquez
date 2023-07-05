package com.ronald.demopokedex.repository

import com.ronald.demopokedex.data.remote.PokeApi
import com.ronald.demopokedex.data.remote.responses.Pokemon
import com.ronald.demopokedex.data.remote.responses.PokemonList
import com.ronald.demopokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
){
    suspend fun getPokemon(limit: Int, offset: Int): Resource<PokemonList> {
        val response: PokemonList = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("Ocurrio un error")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInf(name: String): Resource<Pokemon> {
        val response: Pokemon = try {
            api.getPokemonByName(name )
        } catch (e: Exception) {
            return Resource.Error("Ocurrio un error")
        }
        return Resource.Success(response)
    }
}