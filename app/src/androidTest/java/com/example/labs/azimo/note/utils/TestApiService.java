package com.example.labs.azimo.note.utils;

import com.example.labs.azimo.note.api.mock.CloudMock;
import com.example.labs.azimo.note.api.mock.CloudMockResponse;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class TestApiService {

    private CloudMock cloudMock;

    public TestApiService(CloudMock cloudMock) throws Exception {
        this.cloudMock = cloudMock;
    }

    public CloudMockResponse registerUser(final String email, final String password) {
        return cloudMock.registerUser(email, password).toBlocking().first();
    }
}
