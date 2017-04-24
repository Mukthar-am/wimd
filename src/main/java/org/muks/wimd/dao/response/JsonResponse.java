package org.muks.wimd.dao.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by 300000511 on 25/04/17.
 */

@JsonSerialize
public abstract class JsonResponse {
    public abstract String getResponse();
}
