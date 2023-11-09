package com.android.course.room

import android.app.Application
import androidx.room.Room
import com.android.course.room.data.db.FilmsDb
import com.android.course.room.data.db.entities.FilmEntity
import com.android.course.room.data.db.entities.GenreEntity
import com.android.course.room.di.AppComponent
import com.android.course.room.di.DaggerAppComponent
import com.android.course.room.di.RepoModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class App : Application() {

    private lateinit var db: FilmsDb
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, FilmsDb::class.java, "Films.db")
            .build()

        appComponent = DaggerAppComponent.builder()
            .repoModule(RepoModule(db.filmDao()))
            .build()

        populateDb()
    }

    //ToDo Вынести
    private fun populateDb() {
        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            if (db.filmDao().getAllFilms().isEmpty()) {
                db.filmDao().insertGenre(GenreEntity(name = "Боевик"))
                db.filmDao().insertGenre(GenreEntity(name = "Ужасы"))
                db.filmDao().insertGenre(GenreEntity(name = "Фантастика"))
                db.filmDao().insertGenre(GenreEntity(name = "Сериал"))

                db.filmDao().insertFilms(
                    FilmEntity(
                        title = "Терминатор",
                        year = 1984,
                        genreId = 1,
                        info = "Терминатор. Это слово уже очень давно стало нарицательным, подразумевая под собой не только величайший фантастический боевик всех времен и народов, но и целое направление в мировой фантастике, веху в истории кинематографа, повальное увлечение робототехникой и легендарный культ не одного поколения, неразрывно связанный с именами Кэмерона и Шварценеггера, подаривших нам бессмертный образ киборга-убийцы из будущего – Терминатора.",
                        rate = 8.9
                    )
                )
                db.filmDao().insertFilms(
                    FilmEntity(
                        title = "Крик",
                        year = 2001,
                        genreId = 2,
                        info = "Спустя 25 лет после жестоких убийств, потрясших тихий городок Вудсборо, и выхода серии культовых слэшеров на основе тех событий старшеклассница Тара подвергается нападению, такому же, как и в фильме. Узнав о случившемся, в Вудсборо возвращается её старшая сестра Сэм и просит бывшего шерифа городка Дьюи Райли помощи в поимке нового убийцы, скрывающегося за маской Призрачного лица.",
                        rate = 6.2
                    )
                )
                db.filmDao().insertFilms(
                    FilmEntity(
                        title = "Мстители",
                        year = 2012,
                        genreId = 3,
                        info = "Локи, сводный брат Тора, возвращается, и в этот раз он не один. Земля оказывается на грани порабощения, и только лучшие из лучших могут спасти человечество. Глава международной организации Щ.И.Т. Ник Фьюри собирает выдающихся поборников справедливости и добра, чтобы отразить атаку. Под предводительством Капитана Америки Железный Человек, Тор, Невероятный Халк, Соколиный Глаз и Чёрная Вдова вступают в войну с захватчиком.",
                        rate = 7.9
                    )
                )
                db.filmDao().insertFilms(
                    FilmEntity(
                        title = "Крепкий орешек",
                        year = 1988,
                        genreId = 1,
                        info = "В суперсовременном небоскребе Лос-Анджелеса полицейский Джон Макклейн ведет смертельную схватку с бандой политических террористов, взявших в заложники два десятка человек, в число которых попадает и его жена. Началось все с того, что парень приехал в город к жене, оказался на рождественском приеме, а кончилось настоящей войной...",
                        rate = 8.0
                    )
                )
                db.filmDao().insertFilms(
                    FilmEntity(
                        title = "Во все тяжкие",
                        year = 2008,
                        genreId = 4,
                        info = "Школьный учитель химии Уолтер Уайт узнаёт, что болен раком лёгких. Учитывая сложное финансовое состояние дел семьи, а также перспективы, Уолтер решает заняться изготовлением метамфетамина. Для этого он привлекает своего бывшего ученика Джесси Пинкмана, когда-то исключённого из школы при активном содействии Уайта. Пинкман сам занимался варкой мета, но накануне, в ходе рейда УБН, он лишился подельника и лаборатории.",
                        rate = 9.0
                    )
                )
                db.filmDao().insertFilms(
                    FilmEntity(
                        title = "Атака титанов",
                        year = 2013,
                        genreId = 4,
                        info = "Уже многие годы человечество ведёт борьбу с титанами — огромными существами, которые не обладают особым интеллектом, зато едят людей и получают от этого удовольствие. После продолжительной борьбы остатки человечества построили высокую стену, окружившую страну людей, через которую титаны пройти не могли. С тех пор прошло сто лет, люди мирно живут под защитой стены. Но однажды подросток Эрэн и его сводная сестра Микаса становятся свидетелями страшного события — участок стены разрушается супертитаном, появившимся прямо из воздуха. Титаны нападают на город, и дети в ужасе видят, как один из монстров заживо съедает их мать. Эрэн клянётся, что убьёт всех титанов и отомстит за человечество.",
                        rate = 9.0
                    )
                )
            }
            cancel()
        }
    }
}