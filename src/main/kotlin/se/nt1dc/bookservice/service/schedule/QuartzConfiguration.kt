package se.nt1dc.bookservice.service.schedule

import org.quartz.JobDetail
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean


@Configuration
class QuartzConfiguration {
    @Bean
    fun jobDetail(): JobDetailFactoryBean {
        val jobDetailFactory = JobDetailFactoryBean()
        jobDetailFactory.setJobClass(BestJobEver::class.java)
        jobDetailFactory.setDurability(true)
        return jobDetailFactory
    }

    @Bean
    fun trigger(jobDetail: JobDetail?): SimpleTriggerFactoryBean {
        val trigger = SimpleTriggerFactoryBean()
        trigger.setJobDetail(jobDetail!!)
        trigger.setRepeatInterval(5 * 1000) // Execute every 5 seconds
        return trigger
    }
}

