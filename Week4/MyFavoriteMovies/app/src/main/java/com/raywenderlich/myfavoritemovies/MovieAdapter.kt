package com.raywenderlich.myfavoritemovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import java.time.LocalDate
import java.time.Month

class MovieAdapter : Adapter<MovieViewHolder>() {
    private val movies = mutableListOf(
        Movie(
            1,
            LocalDate.of(1999, Month.MAY, 16),
            "Star Wars Episode I: The Phantom Menace",
            "Obi-Wan Kenobi (Ewan McGregor) is a young apprentice Jedi knight under the " +
                    "tutelage of Qui-Gon Jinn (Liam Neeson) ; Anakin Skywalker (Jake Lloyd), who will " +
                    "later father Luke Skywalker and become known as Darth Vader, is just a 9-year-old " +
                    "boy. When the Trade Federation cuts off all routes to the planet Naboo, Qui-Gon " +
                    "and Obi-Wan are assigned to settle the matter.",
            "Science Fiction",
            R.drawable.star_wars_episode_i
        ),
        Movie(
            2,
            LocalDate.of(2002, Month.MAY, 12),
            "Star Wars Episode II:  The Phantom Menace",
            "Set ten years after the events of \"The Phantom Menace,\" the Republic continues " +
                    "to be mired in strife and chaos. A separatist movement encompassing hundreds of " +
                    "planets and powerful corporate alliances poses new threats to the galaxy that " +
                    "even the Jedi cannot stem. These moves, long planned by an as yet unrevealed " +
                    "and powerful force, lead to the beginning of the Clone Wars -- and the beginning " +
                    "of the end of the Republic.",
            "Science Fiction",
            R.drawable.star_wars_episode_ii
        ),
        Movie(
            3,
            LocalDate.of(2005, Month.MAY, 15),
            "Star Wars Episode III: Revenge of the Sith",
            "It has been three years since the Clone Wars began. Jedi Master Obi-Wan Kenobi " +
                    "(Ewan McGregor) and Jedi Knight Anakin Skywalker (Hayden Christensen) rescue " +
                    "Chancellor Palpatine (Ian McDiarmid) from General Grievous, the commander of the " +
                    "droid armies, but Grievous escapes. Suspicions are raised within the Jedi Council " +
                    "concerning Chancellor Palpatine, with whom Anakin has formed a bond. Asked to spy " +
                    "on the chancellor, and full of bitterness toward the Jedi Council, Anakin " +
                    "embraces the Dark Side.",
            "Science Fiction",
            R.drawable.star_wars_episode_iii
        ),
        Movie(
            4,
            LocalDate.of(1977, Month.MAY, 25),
            "Star Wars",
            "The Imperial Forces -- under orders from cruel Darth Vader (David Prowse) " +
                    "-- hold Princess Leia (Carrie Fisher) hostage, in their efforts to quell the" +
                    " rebellion against the Galactic Empire. Luke Skywalker (Mark Hamill) and " +
                    "Han Solo (Harrison Ford), captain of the Millennium Falcon, work together" +
                    " with the companionable droid duo R2-D2 (Kenny Baker) and C-3PO " +
                    "(Anthony Daniels) to rescue the beautiful princess, help the Rebel Alliance, " +
                    "and restore freedom and justice to the Galaxy.",
            "Science Fiction",
            R.drawable.star_wars_episode_iv
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_view_holder, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTextView.text = movies[position].title
        holder.movieTextView.setCompoundDrawablesWithIntrinsicBounds(
            movies[position].poster,
            0,
            0,
            0
        )
    }
}