package AddFive

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.google.gson.Gson

private val gson = Gson()

class Handler : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    override fun handleRequest(event: APIGatewayProxyRequestEvent, context: Context): APIGatewayProxyResponseEvent {
        println(event.body)
        val request = gson.fromJson(event.body, Request::class.java)
        val response = Response(request.input + 5)
        return APIGatewayProxyResponseEvent().withStatusCode(200).withBody(gson.toJson(response))
    }
}

data class Request(
    val input: Int
)

data class Response(
    val output: Int
)
