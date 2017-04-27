package org.muks.wimd.dao.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.json.simple.JSONObject;

/**
 * Created by 300000511 on 25/04/17.
 */

@JsonSerialize
public abstract class JsonResponse {
    public abstract JSONObject getResponse();
}
