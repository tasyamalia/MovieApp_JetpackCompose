package com.tasyamalia.movieapp.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tasyamalia.movieapp.model.Movie
import com.tasyamalia.movieapp.model.getMovies
import com.tasyamalia.movieapp.navigation.MovieScreens
import com.tasyamalia.movieapp.ui.theme.MovieAppTheme
import com.tasyamalia.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    MovieAppTheme(darkTheme = false) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier.shadow(elevation = 5.dp),
                    title = {
                        Text("Movie App")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.LightGray,
                    )
                )
            }
        ) { innerPadding ->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//            ) {
//
//            }
            MainContent(
                innerPadding = innerPadding,
                navController = navController
            )
        }

    }


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent(
    innerPadding: PaddingValues,
    navController: NavController,
    movieList: List<Movie> = getMovies()) {
    Column(Modifier.padding(top = innerPadding.calculateTopPadding(),
        start = innerPadding.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr)+ 12.dp,
        end = innerPadding.calculateLeftPadding(layoutDirection = LayoutDirection.Rtl)+ 12.dp),) {
        LazyColumn {
            itemsIndexed(items = movieList) {index, movie ->
                Surface(modifier = Modifier.padding(
                    top = if (index == 0) 12.dp else 0.dp,
                    bottom = if (index == movieList.size-1) innerPadding.calculateBottomPadding() + 12.dp else 0.dp)){
                    MovieRow(movie = movie, onItemClick = { movie ->
                        navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")

                    })
                }
            }
        }
    }
}

