package co.com.ceiba.mobile.androidtestceiba.data.local.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import co.com.ceiba.mobile.androidtestceiba.data.local.database.CeibaDatabase
import co.com.ceiba.mobile.androidtestceiba.domain.models.Post
import co.com.ceiba.mobile.androidtestceiba.domain.models.User
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Prueba unitaria para comprobar el funcionamiento de PostDao
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class PostDaoTest : TestCase() {

  private lateinit var database: CeibaDatabase
  private lateinit var postDao : PostDao
  private lateinit var userDao : UserDao
  private val roomPosts: List<Post> = listOf(
    Post(1,1,"Camino", "Largas Caminatas"),
    Post(2,1,"Cielo", "El cielo azul"),
    Post(3,2,"Prado", "Los prados del ensueño"),
    Post(4,2,"Moto", "La moto deportiva"),
    Post(5,2,"Ciudad", "Los grandes rascacielos"),
    Post(6,3,"Universo", "La infinidad del universo"),
    Post(7,3,"Lluvia", "La vida contenida en sus gota")
  )

  @Before
  public override fun setUp() {
    super.setUp()
    database = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(), CeibaDatabase::class.java
    ).allowMainThreadQueries().build()
    postDao = database.postDao
    userDao = database.userDao
    runBlocking {
      val users = roomPosts.map { post->
        User(post.userId, "Name","1-2-3-4-5-6-7-8-9","nombre@email.com")
      }
      userDao.insertUsers(users)
    }
  }

  @After
  @Throws(IOException::class)
  fun teardown(){
    database.close()
  }


  /**
   * Prueba unitaria para la función que inserta el listado de post
   */
  @Test
  @Throws(Exception::class)
  fun insertPosts() = runBlocking {
    postDao.insertPosts(roomPosts)
    val postQuantity = postDao.getPostsQuantity()
    assertEquals(roomPosts.size, postQuantity)
  }

  /**
   * Prueba unitaria para la función que trae el listado de post filtrado por el userId
   */
  @Test
  @Throws(Exception::class)
  fun getPostByUserId() = runBlocking {
    val userId = 2
    postDao.insertPosts(roomPosts)
    val postsByUserInBd = postDao.getPostByUserId(userId)
    val postsByUserLocal = roomPosts.filter { user-> user.userId == userId }
    assertEquals(postsByUserLocal, postsByUserInBd)
  }

  /**
   * Prueba unitaria para la función que trae el numero de post existentes en la BD
   */
  @Test
  @Throws(Exception::class)
  fun getPostsQuantity() = runBlocking {
    postDao.insertPosts(roomPosts)
    val postQuantityInBd = postDao.getPostsQuantity()
    val postQuantityLocal = roomPosts.size
    assertEquals(postQuantityInBd, postQuantityLocal)
  }

}