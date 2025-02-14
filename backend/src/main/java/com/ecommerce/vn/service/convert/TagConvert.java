package com.ecommerce.vn.service.convert;

import com.ecommerce.vn.dto.tag.TagCreateRequest;
import com.ecommerce.vn.dto.tag.TagResponse;
import com.ecommerce.vn.dto.tag.TagUpdateRequest;
import com.ecommerce.vn.entity.product.Tag;

public class TagConvert {

    public Tag tagCreateConvert(TagCreateRequest tagCreateRequest) {
        if (tagCreateRequest == null) {
            return null;
        }

        Tag tag = new Tag();
        tag.setTagName(tagCreateRequest.getTagName());
         

        return tag;
    }

    public Tag tagUpdateConvert(TagUpdateRequest tagUpdateRequest, Tag tag) {
        if (tagUpdateRequest == null || tag == null) {
            return null;
        }

        tag.setTagName(tagUpdateRequest.getTagName());
        tag.setActive(tagUpdateRequest.getActive());

        return tag;
    }

    public TagResponse tagConvertToTagResponse(Tag tag) {
        if (tag == null) {
            return null;
        }

        return new TagResponse(
                tag.getId(),
                tag.getTagName(),
                tag.getActive(),
                tag.getCreatedAt(),
                tag.getUpdateAt(),
                tag.getCreatedBy(),
                tag.getUpdatedBy()
        );
    }
}
