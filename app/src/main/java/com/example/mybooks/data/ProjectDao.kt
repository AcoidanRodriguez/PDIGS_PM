package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * from projects_table ORDER BY title ASC")
    fun getAllProjects(): Flow<List<Project>>

    @Query("SELECT COUNT(*) FROM projects_table")
    fun getTotalProjects(): Int

    @Query("SELECT * from projects_table WHERE username = :username")
    fun getUser(username: String): Flow<Project>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(project: Project)

    @Update
    suspend fun update(project: Project)

    @Delete
    suspend fun delete(project: Project)

    @Update
    suspend fun updateProject(project: Project)


    @Query("SELECT userStories FROM projects_table WHERE id = :projectId")
    fun getUserStoriesForProject(projectId: Int): Flow<List<String>>


    @Query("UPDATE projects_table SET userStories = userStories || :userStory WHERE id = :projectId")
    suspend fun addUserStoryToProject(projectId: Int, userStory: String)

    @Query("SELECT * FROM projects_table WHERE id = :projectId")
    suspend fun getProjectById(projectId: Int): Project?

    @Query("SELECT userStoriesForSprint FROM projects_table WHERE id = :projectId")
    fun getUserStoriesForSprintForProject(projectId: Int): Flow<List<String>>

    @Query("UPDATE projects_table SET userStoriesForSprint = userStoriesForSprint || :userStory WHERE id = :projectId")
    suspend fun addUserStoryForSprintToProject(projectId: Int, userStory: String)

}
