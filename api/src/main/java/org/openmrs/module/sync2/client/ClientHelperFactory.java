package org.openmrs.module.sync2.client;

import org.openmrs.module.fhir.api.constants.ClientHelperConstants;
import org.openmrs.module.fhir.api.helper.ClientHelper;
import org.openmrs.module.sync2.SyncConstants;
import org.openmrs.module.sync2.api.utils.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientHelperFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientHelperFactory.class);

	public static ClientHelper createClient(final String clientType) {
		ClientHelper clientHelper = ContextUtils.getRegisteredComponentSafely(
				ClientHelperConstants.CLIENT_HELPER_COMPONENT_PREFIX + clientType,
				ClientHelper.class);
		if(clientHelper != null) {
			return clientHelper;
		} else {
			LOGGER.warn(String.format("Unrecognized clientType: %s. The REST Client will be used.", clientType));
			return ContextUtils.getRegisteredComponentSafely(
					ClientHelperConstants.CLIENT_HELPER_COMPONENT_PREFIX + SyncConstants.REST_CLIENT,
					ClientHelper.class);
		}
	}
}
