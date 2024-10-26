package com.alekpajor.pbd

import com.alekpajor.pbd.Common.Points
import com.alekpajor.pbd.Models.Exercise
import com.alekpajor.pbd.Models.Snapshot
import com.alekpajor.pbd.Repository.*
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Setup(
    private val activityRepository: ActivityRepository,
    private val reportRepository: ReportRepository,
    private val snapshotRepository: SnapshotRepository,
    private val userRepository: UserRepository,
    private val exerciseRepository: ExerciseRepository,
) {
    @Bean
    fun commandLineRunner(): CommandLineRunner {
        return CommandLineRunner {
            val inputData = """
                anterior body extension
                [172,172,131,38] 00:01:86
                [179,178,17,31] 00:04:06
                [178,179,93,35] 00:06:46
                [136,143,39,36] 00:09:60
                [178,177,127,129] 00:11:06
                [178,176,15,45] 00:13:23
                [148,156,41,50] 00:15:56
                [136,148,30,37] 00:17:36
                [179,179,65,87] 00:20:00
                anterior limb extension
                [172,167,128,52] 00:00:70
                [177,130,54,63] 00:02:13
                [170,166,145,60] 00:03:50
                [167,166,66,53] 00:05:00
                [178,165,65,145] 00:06:26
                [154,124,58,46] 00:07:63
                [177,162,70,143] 00:08:33
                [176,129,91,55] 00:11:00
                lateral hand extensions
                [178,176,2,44] 00:01:80
                [171,147,3,1] 00:02:80
                [144,133,155,52] 00:04:60
                [173,176,20,43] 00:06:53
                [139,134,9,18] 00:08:46
                [178,174,1,16] 00:10:20
                [127,131,5,9] 00:12:90
                [173,174,4,4] 00:14:40
                [167,151,9,1] 00:16:63
                example
                [74,114,179,166] 00:00:96
                [76,96,176,165] 00:02:26
                [168,176,173,163] 00:03:30
                [60,78,176,166] 00:04:36
                [171,172,177,162] 00:05:40
                [64,84,175,165] 00:06:33
                [99,155,174,169] 00:07:33
                [174,177,179,171] 00:08:33
                [156,142,169,75] 00:10:10
                [152,164,66,164] 00:12:23
            """.trimIndent()

            val lines = inputData.lines()
            var currentExercise: Exercise? = null
            val exercises = mutableListOf<Exercise>()

            lines.forEach { line ->
                if (!line.startsWith("[")) {
                    if (currentExercise != null) {
                        exercises.add(currentExercise!!)
                    }
                    val capitalizedLine = line.trim().replaceFirstChar { it.uppercase() }
                    currentExercise = Exercise(name = capitalizedLine, snapshots = mutableListOf())
                } else if (currentExercise != null) {
                    val snapshotData = line.split(" ")
                    val pointsData = snapshotData[0]
                        .removePrefix("[")
                        .removeSuffix("]")
                        .split(",")
                        .map { it.toInt() }

                    val time = snapshotData[1]

                    val snapshot = Snapshot(
                        time = time,
                        points = Points(
                            leftElbow = pointsData[0],
                            rightElbow = pointsData[1],
                            leftKnee = pointsData[2],
                            rightKnee = pointsData[3]
                        ),
                        exercise = currentExercise
                    )
                    currentExercise!!.snapshots.add(snapshot)
                }
            }
            if (currentExercise != null) {
                exercises.add(currentExercise!!)
            }
            exerciseRepository.saveAll(exercises)
        }
    }
}