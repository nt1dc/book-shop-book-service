package se.nt1dc.bookservice.service

import com.netflix.discovery.EurekaClient
import org.springframework.stereotype.Service

@Service
class EurekaClientService(
    val discoveryClient: EurekaClient
) {
    val serviceMap: Map<String, String> = HashMap()
    fun getServiceUrl(serviceName: String): String {
        /**
         *TODO: сделать кеширование адресов
         * ладно это оверинженеринг (((((((
         * Типо сначала проверяем в мапе пробуем получить адрес
         */
        return discoveryClient.getNextServerFromEureka(serviceName, false).homePageUrl
    }
}