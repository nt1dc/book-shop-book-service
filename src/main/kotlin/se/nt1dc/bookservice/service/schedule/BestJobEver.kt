package se.nt1dc.bookservice.service.schedule

import org.quartz.Job
import org.quartz.JobExecutionContext

class BestJobEver:Job {
    override fun execute(context: JobExecutionContext?) {
        println("straighten your back!")
    }
}