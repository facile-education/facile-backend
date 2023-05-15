/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import i18n from '../../../common/I18n';
import InviteTeamMembersForm from '../../../common/containers/setup-forms/InviteTeamMembersForm';
import SetupAnalyticsCloudForm from '../../../common/containers/setup-forms/SetupAnalyticsCloudForm';
import SetupDXPCloudForm from '../../../common/containers/setup-forms/SetupDXPCloudForm';
import {useAppPropertiesContext} from '../../../common/contexts/AppPropertiesContext';
import {PAGE_ROUTER_TYPES} from '../../../common/utils/constants';
import {PRODUCT_TYPES} from '../../customer-portal/utils/constants';
import {useOnboarding} from '../context';
import {actionTypes} from '../context/reducer';
import {ONBOARDING_STEP_TYPES} from '../utils/constants';
import SuccessCloud from './SuccessCloud';
import Welcome from './Welcome';

const Pages = () => {
	const [
		{
			analyticsCloudActivationSubmittedStatus,
			dxpCloudActivationSubmittedStatus,
			project,
			sessionId,
			step,
			subscriptionGroups,
			totalAdministratorAccounts,
		},
		dispatch,
	] = useOnboarding();
	const {client} = useAppPropertiesContext();

	const subscriptionDXPCloud = subscriptionGroups?.find(
		(subscriptionGroup) => subscriptionGroup.name === PRODUCT_TYPES.dxpCloud
	);

	const subscriptionAnalyticsCloud = subscriptionGroups?.find(
		(subscriptionGroup) =>
			subscriptionGroup.name === PRODUCT_TYPES.analyticsCloud
	);

	const invitesPageHandle = () => {
		if (subscriptionDXPCloud && !dxpCloudActivationSubmittedStatus) {
			return dispatch({
				payload: ONBOARDING_STEP_TYPES.dxpCloud,
				type: actionTypes.CHANGE_STEP,
			});
		}
		if (
			subscriptionAnalyticsCloud &&
			!analyticsCloudActivationSubmittedStatus
		) {
			return dispatch({
				payload: ONBOARDING_STEP_TYPES.analyticsCloud,
				type: actionTypes.CHANGE_STEP,
			});
		}
		window.location.href = PAGE_ROUTER_TYPES.project(project.accountKey);
	};

	const dxpCloudPageHandle = () => {
		if (
			subscriptionAnalyticsCloud &&
			!analyticsCloudActivationSubmittedStatus
		) {
			return dispatch({
				payload: ONBOARDING_STEP_TYPES.analyticsCloud,
				type: actionTypes.CHANGE_STEP,
			});
		}
		window.location.href = PAGE_ROUTER_TYPES.project(project.accountKey);
	};

	const analyticsCloudPageHandle = () => {
		window.location.href = PAGE_ROUTER_TYPES.project(project.accountKey);
	};

	const availableAdministratorAssets =
		project && project.maxRequestors - totalAdministratorAccounts;

	const StepsLayout = {
		[ONBOARDING_STEP_TYPES.invites]: {
			Component: (
				<InviteTeamMembersForm
					availableAdministratorAssets={availableAdministratorAssets}
					handlePage={invitesPageHandle}
					leftButton={i18n.translate('skip-for-now')}
					project={project}
					sessionId={sessionId}
				/>
			),
		},
		[ONBOARDING_STEP_TYPES.dxpCloud]: {
			Component: (
				<SetupDXPCloudForm
					client={client}
					handlePage={(isSuccess) => {
						if (isSuccess) {
							return dispatch({
								payload: ONBOARDING_STEP_TYPES.successDxpCloud,
								type: actionTypes.CHANGE_STEP,
							});
						}
						dxpCloudPageHandle();
					}}
					leftButton={i18n.translate('skip-for-now')}
					project={project}
					subscriptionGroupId={
						subscriptionDXPCloud?.accountSubscriptionGroupId
					}
				/>
			),
		},
		[ONBOARDING_STEP_TYPES.successDxpCloud]: {
			Component: (
				<SuccessCloud
					handlePage={dxpCloudPageHandle}
					productType={PRODUCT_TYPES.dxpCloud}
				/>
			),
		},
		[ONBOARDING_STEP_TYPES.welcome]: {
			Component: <Welcome />,
			Skeleton: <Welcome.Skeleton />,
		},
		[ONBOARDING_STEP_TYPES.analyticsCloud]: {
			Component: (
				<SetupAnalyticsCloudForm
					client={client}
					handlePage={(isSuccess) => {
						if (isSuccess) {
							return dispatch({
								payload:
									ONBOARDING_STEP_TYPES.successAnalyticsCloud,
								type: actionTypes.CHANGE_STEP,
							});
						}
						analyticsCloudPageHandle();
					}}
					leftButton={i18n.translate('skip-for-now')}
					project={project}
					subscriptionGroupId={
						subscriptionAnalyticsCloud?.accountSubscriptionGroupId
					}
				/>
			),
		},
		[ONBOARDING_STEP_TYPES.successAnalyticsCloud]: {
			Component: (
				<SuccessCloud
					handlePage={analyticsCloudPageHandle}
					productType={PRODUCT_TYPES.analyticsCloud}
				/>
			),
		},
	};

	if (project && subscriptionGroups) {
		return StepsLayout[step].Component;
	}

	return StepsLayout[ONBOARDING_STEP_TYPES.welcome].Skeleton;
};

export default Pages;
