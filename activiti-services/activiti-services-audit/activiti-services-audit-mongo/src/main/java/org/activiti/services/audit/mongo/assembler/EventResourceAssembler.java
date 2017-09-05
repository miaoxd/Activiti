/*
 * Copyright 2017 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.services.audit.mongo.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.activiti.services.audit.mongo.ProcessEngineEventsController;
import org.activiti.services.audit.mongo.entity.EventLogDocument;
import org.activiti.services.audit.mongo.resources.EventResource;
import org.bson.types.ObjectId;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class EventResourceAssembler implements ResourceAssembler<EventLogDocument, EventResource> {

    @Override
    public EventResource toResource(EventLogDocument document) {
        String id = ((ObjectId) document.get("_id")).toString();
        document.put("id", id);
        document.remove("_id");
        Link selfRel = linkTo(methodOn(ProcessEngineEventsController.class).findById(id)).withSelfRel();
        return new EventResource(document, selfRel);
    }

}