package br.com.blog.web.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.blog.web.event.ResourceCreatedEvent;

import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;

@Component
public class ResourceCreatedDiscoverabilityListener implements ApplicationListener<ResourceCreatedEvent> {

    @Override
    public void onApplicationEvent(final ResourceCreatedEvent resourceCreatedEvent) {
        Preconditions.checkNotNull(resourceCreatedEvent);

        final HttpServletResponse response = resourceCreatedEvent.getResponse();
        final long idOfNewResource = resourceCreatedEvent.getIdOfNewResource();

        addLinkHeaderOnResourceCreation(response, idOfNewResource);
    }

    void addLinkHeaderOnResourceCreation(final HttpServletResponse response, final long idOfNewResource) {
        // final String requestUrl = request.getRequestURL().toString();
        // final URI uri = new UriTemplate("{requestUrl}/{idOfNewResource}").expand(requestUrl, idOfNewResource);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idOfNewResource}").buildAndExpand(idOfNewResource).toUri();
        response.setHeader(HttpHeaders.LOCATION, uri.toASCIIString());
    }
}