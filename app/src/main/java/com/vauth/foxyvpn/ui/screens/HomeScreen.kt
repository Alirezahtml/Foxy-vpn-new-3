package com.vauth.foxyvpn.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Waves
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vauth.foxyvpn.FoxyVpnApp
import com.vauth.foxyvpn.R
import com.vauth.foxyvpn.data.GUARDIAN_ENDPOINT_DEFAULT
import com.vauth.foxyvpn.data.RECOMMENDED_COUNTRY_CODE
import com.vauth.foxyvpn.data.ServerListClient
import com.vauth.foxyvpn.data.model.ConnectionState
import com.vauth.foxyvpn.data.model.ProxyCandidate
import com.vauth.foxyvpn.data.model.emojiFlag
import com.vauth.foxyvpn.ui.components.DynamicIsland
import com.vauth.foxyvpn.ui.theme.AnimatedOdometerText
import com.vauth.foxyvpn.ui.theme.AnimatedWorldMapBackground
import com.vauth.foxyvpn.ui.theme.FoxyLanguage
import com.vauth.foxyvpn.ui.theme.LanguageController
import com.vauth.foxyvpn.ui.theme.LiveTrafficSparkline
import com.vauth.foxyvpn.ui.theme.LocalFoxyStatusColors
import com.vauth.foxyvpn.ui.theme.NeumorphicCard
import com.vauth.foxyvpn.ui.theme.NeumorphicIconButton
import com.vauth.foxyvpn.ui.theme.NeumorphicRadarPulse
import com.vauth.foxyvpn.ui.theme.NeumorphicSlideToConnect
import com.vauth.foxyvpn.ui.theme.StyleAtmospherePosterOverlay
import com.vauth.foxyvpn.ui.theme.TelegramThemeAppearanceIcon
import com.vauth.foxyvpn.ui.theme.ThemeController
import com.vauth.foxyvpn.ui.theme.ThemeMode
import com.vauth.foxyvpn.ui.theme.currentFoxyFonts
import com.vauth.foxyvpn.ui.theme.currentFoxyStrings
import com.vauth.foxyvpn.ui.theme.currentNeumorphicColors
import com.vauth.foxyvpn.ui.theme.formatBytesLocalized
import com.vauth.foxyvpn.ui.theme.formatClockTimeLocalized
import com.vauth.foxyvpn.ui.theme.formatDurationLocalized
import com.vauth.foxyvpn.ui.theme.formatMinutesLocalized
import com.vauth.foxyvpn.ui.theme.formatPingLocalized
import com.vauth.foxyvpn.ui.theme.formatSpeedLocalized
import com.vauth.foxyvpn.ui.theme.localizeCountryName
import com.vauth.foxyvpn.ui.theme.localizeDigits
import com.vauth.foxyvpn.ui.theme.neumorphicSunken
import com.vauth.foxyvpn.ui.theme.neumorphicTouchBounce
import com.vauth.foxyvpn.ui.theme.shakeOnTrigger
import com.vauth.foxyvpn.ui.theme.staggeredEntrance
import com.vauth.foxyvpn.ui.theme.subtleFloatAnimation
import com.vauth.foxyvpn.vpn.ExitCheck
import com.vauth.foxyvpn.vpn.FoxyVpnService
import com.vauth.foxyvpn.vpn.IpLocationInfo
import com.vauth.foxyvpn.vpn.PingUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    app: FoxyVpnApp,
    themeController: ThemeController,
    languageController: LanguageController,
    onRequestConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onOpenServers: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenTools: (Int) -> Unit = {},
) {
    val state by FoxyVpnService.state.collectAsState()
    val lastError by FoxyVpnService.lastError.collectAsState()
    val liveStats by FoxyVpnService.vpnLiveStats.collectAsState()
    val connectedAtEpochMs by FoxyVpnService.connectedAtEpochMs.collectAsState()
    val pingLatency by FoxyVpnService.pingLatencyMs.collectAsState()
    val vpnEdgeIp by FoxyVpnService.vpnEdgeIp.collectAsState()

    val selectedProxy by app.proxyStateStore.selectedProxyFlow.collectAsState()

    val neu = currentNeumorphicColors
    val statusColors = LocalFoxyStatusColors.current
    val s = currentFoxyStrings
    val fonts = currentFoxyFonts
    val systemInDarkTheme = isSystemInDarkTheme()
    val haptics = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()

    var elapsedSeconds by remember { mutableLongStateOf(0L) }
    var realIp by remember {
        mutableStateOf<IpLocationInfo?>(
            app.settingsStore.cachedRealIp?.let {
                IpLocationInfo(it, app.settingsStore.cachedRealCountry.orEmpty())
            },
        )
    }
    var isPinging by remember { mutableStateOf(false) }
    var disconnectedPingLatency by remember { mutableStateOf<Int?>(null) }
    var quotaRemaining by remember { mutableStateOf(app.settingsStore.cachedQuotaRemaining) }
    var quotaMax by remember { mutableStateOf(app.settingsStore.cachedQuotaMax) }
    var isUnlimited by remember { mutableStateOf(app.settingsStore.cachedIsUnlimited) }

    // Rolling 24-sample history for live traffic waveform
    val rxHistory = remember { mutableStateListOf<Long>().apply { repeat(24) { add(0L) } } }
    val txHistory = remember { mutableStateListOf<Long>().apply { repeat(24) { add(0L) } } }

    LaunchedEffect(liveStats, state) {
        if (state == ConnectionState.CONNECTED) {
            if (rxHistory.size >= 24) rxHistory.removeAt(0)
            rxHistory.add(liveStats.rxRateBytesPerSec)
            if (txHistory.size >= 24) txHistory.removeAt(0)
            txHistory.add(liveStats.txRateBytesPerSec)
        } else {
            for (i in 0 until rxHistory.size) rxHistory[i] = 0L
            for (i in 0 until txHistory.size) txHistory[i] = 0L
        }
    }

    LaunchedEffect(state) {
        if (state == ConnectionState.CONNECTED) {
            quotaRemaining = app.settingsStore.cachedQuotaRemaining
            quotaMax = app.settingsStore.cachedQuotaMax
            isUnlimited = app.settingsStore.cachedIsUnlimited
        }
    }

    LaunchedEffect(state, connectedAtEpochMs) {
        if (state == ConnectionState.CONNECTED && connectedAtEpochMs != null) {
            while (isActive) {
                val now = System.currentTimeMillis()
                elapsedSeconds = ((now - connectedAtEpochMs!!) / 1000L).coerceAtLeast(0L)
                delay(1000L)
            }
        } else {
            elapsedSeconds = 0L
        }
    }

    LaunchedEffect(Unit) {
        launch {
            ExitCheck().fetchDirectIpInfo().onSuccess { info ->
                realIp = info
                app.settingsStore.cachedRealIp = info.ip
                app.settingsStore.cachedRealCountry = info.countryCode
            }
        }
        launch {
            val token = app.authRepository.currentAccessToken()
            if (token != null) {
                runCatching { app.guardianClient.fetchUserInfo(GUARDIAN_ENDPOINT_DEFAULT, token) }
                    .onSuccess { entitlement ->
                        val unlimited = !entitlement.limitedBandwidth
                        isUnlimited = unlimited
                        app.settingsStore.cachedIsUnlimited = unlimited
                        entitlement.quotaRemaining?.let {
                            quotaRemaining = it
                            app.settingsStore.cachedQuotaRemaining = it
                        }
                        entitlement.maxBytes?.let {
                            quotaMax = it
                            app.settingsStore.cachedQuotaMax = it
                        }
                        app.tokenStore.updateActiveAccountQuota(
                            remaining = entitlement.quotaRemaining,
                            max = entitlement.maxBytes,
                            unlimited = unlimited,
                        )
                    }
            }
        }
    }

    val themeIconRotation by animateFloatAsState(
        targetValue = when (themeController.mode) {
            ThemeMode.SYSTEM -> 0f
            ThemeMode.LIGHT -> 180f
            ThemeMode.DARK -> 360f
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "theme-icon-rot",
    )

    val heroStatusColor by animateColorAsState(
        targetValue = when (state) {
            ConnectionState.CONNECTED -> statusColors.connected
            ConnectionState.CONNECTING -> statusColors.connecting
            ConnectionState.DISCONNECTED -> neu.accent
        },
        animationSpec = tween(420),
        label = "hero-accent-color",
    )

    Scaffold(
        containerColor = neu.background,
    ) { padding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopCenter,
        ) {
            val scrollState = rememberScrollState()

            // 1. 3D Interactive World Globe & Map Background (tilts & rotates with scroll)
            AnimatedWorldMapBackground(
                isConnected = state == ConnectionState.CONNECTED,
                isConnecting = state == ConnectionState.CONNECTING,
                accentColor = heroStatusColor,
                isDark = neu.isDark,
                scrollOffsetPx = scrollState.value.toFloat(),
                modifier = Modifier.fillMaxSize(),
            )

            // 1B. Full-Screen Skin / Poster Atmosphere Overlay (Google Pixel UI, Claymorphism, Dark Luxury, Neo-Brutalism, Bento UI, Editorial, Neumorphism)
            StyleAtmospherePosterOverlay(
                modifier = Modifier.fillMaxSize(),
            )

            var isMagazineOpen by remember { mutableStateOf(false) }
            val magazineRotation by animateFloatAsState(
                targetValue = if (isMagazineOpen) 180f else 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow,
                ),
                label = "magazine-trigger-rotation",
            )

            Column(
                modifier = Modifier
                    .widthIn(max = 540.dp)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // 2. TOP BRAND HEADER (Moved 20% higher to the standard top position above Dynamic Island, with larger Amsterdam logo & Collapsible Magazine Action Button)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp, bottom = 4.dp),
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            // Top-Left Brand Lockup: First App Icon (44.dp), then Enlarged "Foxy vpn" Amsterdam Signature (50.dp x 168.dp)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                NeumorphicIconButton(
                                    size = 44.dp,
                                    onClick = {
                                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                    },
                                    modifier = Modifier.subtleFloatAnimation(amplitudePx = 3f, durationMs = 2600),
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_foxy_logo_clean),
                                        contentDescription = "Foxy vpn",
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(31.dp),
                                    )
                                }
                                Spacer(Modifier.width(12.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.foxy_brand_amsterdam),
                                    contentDescription = "Foxy vpn",
                                    colorFilter = ColorFilter.tint(neu.textPrimary),
                                    contentScale = ContentScale.Fit,
                                    alignment = Alignment.CenterStart,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(168.dp),
                                )
                            }

                            // Top-Right Compact Controls: Active Skin Pill + Main Collapsible Magazine Button ("دکمه خشابی")
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                // Quick Telegram-Style Skin / Poster Switcher Pill
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .neumorphicTouchBounce()
                                        .clip(RoundedCornerShape(50))
                                        .background(neu.accent.copy(alpha = 0.14f))
                                        .border(1.dp, neu.accent.copy(alpha = 0.34f), RoundedCornerShape(50))
                                        .clickable {
                                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                            themeController.cycleUiDesignStyle()
                                        }
                                        .padding(horizontal = 10.dp, vertical = 6.dp),
                                ) {
                                    TelegramThemeAppearanceIcon(
                                        tint = neu.accent,
                                        modifier = Modifier.size(15.dp),
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = themeController.uiDesignStyle.localizedLabel(s.language),
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontFamily = fonts.headingFont,
                                            fontWeight = FontWeight.ExtraBold,
                                        ),
                                        color = neu.accent,
                                        maxLines = 1,
                                    )
                                }

                                // Main Collapsible Magazine Action Button ("دکمه اصلی خشابی")
                                NeumorphicIconButton(
                                    size = 44.dp,
                                    onClick = {
                                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                        isMagazineOpen = !isMagazineOpen
                                    },
                                    modifier = Modifier.neumorphicTouchBounce(),
                                ) {
                                    Icon(
                                        imageVector = if (isMagazineOpen) Icons.Filled.Close else Icons.Filled.Tune,
                                        contentDescription = s.headerSettingsButton,
                                        tint = if (isMagazineOpen) neu.accent else neu.textPrimary,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .graphicsLayer { rotationZ = magazineRotation },
                                    )
                                }
                            }
                        }
                    }

                    // Collapsible Magazine Action Bar ("خشاب ابزار و شخصی‌سازی با انیمیشن نرم")
                    AnimatedVisibility(
                        visible = isMagazineOpen,
                        enter = expandVertically(
                            expandFrom = Alignment.Top,
                            animationSpec = spring(
                                dampingRatio = 0.82f,
                                stiffness = 360f,
                            ),
                        ) + fadeIn(tween(180)) + scaleIn(
                            initialScale = 0.92f,
                            animationSpec = spring(
                                dampingRatio = 0.82f,
                                stiffness = 360f,
                            ),
                        ),
                        exit = shrinkVertically(
                            shrinkTowards = Alignment.Top,
                            animationSpec = spring(
                                dampingRatio = 0.86f,
                                stiffness = 420f,
                            ),
                        ) + fadeOut(tween(130)) + scaleOut(targetScale = 0.92f),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                        ) {
                            NeumorphicCard(
                                cornerRadius = 20.dp,
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 10.dp),
                                ) {
                                    // Compact Single-Row Magazine Action Buttons Capsule ("خشاب دکمه‌های اصلی")
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .horizontalScroll(rememberScrollState()),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        // Control 1: Telegram-Style Theme Mode Toggle (System / Light / Dark)
                                        val mode = themeController.mode
                                        NeumorphicIconButton(
                                            size = 42.dp,
                                            onClick = {
                                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                                themeController.toggle(systemInDarkTheme)
                                            },
                                            modifier = Modifier.neumorphicTouchBounce(),
                                        ) {
                                            TelegramThemeAppearanceIcon(
                                                tint = if (mode == ThemeMode.LIGHT) Color(0xFFF59E0B) else neu.accent,
                                                modifier = Modifier
                                                    .size(20.dp)
                                                    .graphicsLayer { rotationZ = themeIconRotation },
                                            )
                                        }

                                        // Control 2: UI Design Skin Cycle
                                        NeumorphicIconButton(
                                            size = 42.dp,
                                            onClick = {
                                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                                themeController.cycleUiDesignStyle()
                                            },
                                            modifier = Modifier.neumorphicTouchBounce(),
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Layers,
                                                contentDescription = s.uiDesignStyleTitle,
                                                tint = neu.accent,
                                                modifier = Modifier.size(19.dp),
                                            )
                                        }

                                        // Control 3: Color Palette Quick Switcher
                                        NeumorphicIconButton(
                                            size = 42.dp,
                                            onClick = {
                                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                                themeController.cycleColorTheme()
                                            },
                                            modifier = Modifier.neumorphicTouchBounce(),
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Palette,
                                                contentDescription = s.colorPaletteTitle,
                                                tint = neu.accent,
                                                modifier = Modifier.size(19.dp),
                                            )
                                        }

                                        // Control 4: Connection Wave Animation Switcher
                                        NeumorphicIconButton(
                                            size = 42.dp,
                                            onClick = {
                                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                                themeController.cycleHeroWaveStyle()
                                            },
                                            modifier = Modifier.neumorphicTouchBounce(),
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Waves,
                                                contentDescription = s.heroWaveStyleTitle,
                                                tint = neu.accent,
                                                modifier = Modifier.size(19.dp),
                                            )
                                        }

                                        // Control 5: Speed Test
                                        NeumorphicIconButton(
                                            size = 42.dp,
                                            onClick = {
                                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                                isMagazineOpen = false
                                                onOpenTools(0)
                                            },
                                            modifier = Modifier.neumorphicTouchBounce(),
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Speed,
                                                contentDescription = s.headerSpeedButton,
                                                tint = neu.accent,
                                                modifier = Modifier.size(19.dp),
                                            )
                                        }

                                        // Control 6: Animated Language Switcher (EN -> فا -> عر)
                                        NeumorphicIconButton(
                                            size = 42.dp,
                                            onClick = {
                                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                                languageController.toggle()
                                            },
                                            modifier = Modifier.neumorphicTouchBounce(),
                                        ) {
                                            AnimatedContent(
                                                targetState = languageController.language,
                                                transitionSpec = {
                                                    (slideInVertically { h -> h / 2 } + scaleIn(initialScale = 0.75f) + fadeIn(tween(200)))
                                                        .togetherWith(slideOutVertically { h -> -h / 2 } + scaleOut(targetScale = 0.75f) + fadeOut(tween(150)))
                                                },
                                                label = "lang-switch-micro",
                                            ) { lang ->
                                                Text(
                                                    text = lang.badge,
                                                    style = MaterialTheme.typography.labelMedium.copy(
                                                        fontFamily = fonts.headingFont,
                                                        fontWeight = FontWeight.ExtraBold,
                                                    ),
                                                    color = neu.accent,
                                                    maxLines = 1,
                                                )
                                            }
                                        }

                                        // Control 7: Settings
                                        NeumorphicIconButton(
                                            size = 42.dp,
                                            onClick = {
                                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                                isMagazineOpen = false
                                                onOpenSettings()
                                            },
                                            modifier = Modifier.neumorphicTouchBounce(),
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Settings,
                                                contentDescription = s.headerSettingsButton,
                                                tint = neu.textPrimary,
                                                modifier = Modifier.size(19.dp),
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // 3. Dynamic Island right below Top Brand Header
                DynamicIsland(
                    settingsStore = app.settingsStore,
                    selectedProxy = selectedProxy,
                    onDisconnect = onDisconnect,
                    onRequestConnect = onRequestConnect,
                    onOpenServers = onOpenServers,
                    isSystemOverlay = false,
                )

                Spacer(Modifier.height(6.dp))

                // 4. CONNECTION STATUS & TIMER HERO:
                // Enlarged 320.dp Screen-Filling Harmonic Wave Aura + Full-Width Single-Line Status Badge
                val statusText = when (state) {
                    ConnectionState.CONNECTED -> s.statusConnected
                    ConnectionState.CONNECTING -> s.statusConnecting
                    ConnectionState.DISCONNECTED -> s.statusDisconnected
                }
                val statusColor by animateColorAsState(
                    targetValue = when (state) {
                        ConnectionState.CONNECTED -> statusColors.connected
                        ConnectionState.CONNECTING -> statusColors.connecting
                        ConnectionState.DISCONNECTED -> neu.textSecondary
                    },
                    animationSpec = tween(400),
                    label = "hero-status-color",
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .staggeredEntrance(0),
                ) {
                    // Enlarged 320.dp harmonic wave rings behind the timer & status badge
                    NeumorphicRadarPulse(
                        color = if (state == ConnectionState.DISCONNECTED) neu.accent else statusColor,
                        isActive = state == ConnectionState.CONNECTED,
                        isConnecting = state == ConnectionState.CONNECTING,
                        size = 320.dp,
                        waveStyle = themeController.heroWaveStyle,
                    )

                    // Overlay Column takes full width so SECURE & CONNECTED never truncates or wraps
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                    ) {
                        val formattedTimer = if (state == ConnectionState.CONNECTED) {
                            s.language.formatDurationLocalized(elapsedSeconds, alwaysShowHours = true)
                        } else {
                            s.language.formatDurationLocalized(0L, alwaysShowHours = true)
                        }

                        AnimatedOdometerText(
                            text = formattedTimer,
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontFamily = fonts.monoFont,
                                fontWeight = FontWeight.ExtraBold,
                                fontFeatureSettings = "tnum",
                            ),
                            color = neu.textPrimary,
                        )

                        Spacer(Modifier.height(10.dp))

                        // Full-Width Single-Line Status Pill Directly Below Numeric Timer (Never Truncated)
                        AnimatedContent(
                            targetState = statusText,
                            transitionSpec = { fadeIn(tween(240)) togetherWith fadeOut(tween(180)) },
                            label = "status-label-single-line",
                        ) { label ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(statusColor.copy(alpha = 0.14f))
                                    .border(
                                        width = 1.dp,
                                        color = statusColor.copy(alpha = 0.30f),
                                        shape = RoundedCornerShape(50),
                                    )
                                    .padding(horizontal = 18.dp, vertical = 6.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontFamily = fonts.headingFont,
                                        fontWeight = FontWeight.ExtraBold,
                                        letterSpacing = if (s.isRtl) 0.sp else 1.0.sp,
                                    ),
                                    color = statusColor,
                                    maxLines = 1,
                                    softWrap = false,
                                    overflow = TextOverflow.Visible,
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                // 5. SLIDE-TO-CONNECT PRIMARY CONTROLLER
                NeumorphicSlideToConnect(
                    state = state,
                    lastError = lastError,
                    onRequestConnect = onRequestConnect,
                    onDisconnect = onDisconnect,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .staggeredEntrance(1)
                        .shakeOnTrigger(lastError),
                )

                val error = lastError
                AnimatedVisibility(
                    visible = error != null && state != ConnectionState.CONNECTING,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically(),
                ) {
                    Text(
                        text = error.orEmpty(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    )
                }

                Spacer(Modifier.height(18.dp))

                // 6. 5-TOOL QUICK NETWORK & SECURITY TOOLKIT HUB
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .staggeredEntrance(2),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = s.smartToolsSection,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontFamily = fonts.headingFont,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = if (s.isRtl) 0.sp else 0.8.sp,
                            ),
                            color = neu.textSecondary,
                            maxLines = 1,
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { onOpenTools(0) }
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                        ) {
                            Text(
                                text = s.openAllTools,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontFamily = fonts.headingFont,
                                    fontWeight = FontWeight.Bold,
                                ),
                                color = neu.accent,
                                maxLines = 1,
                            )
                            Spacer(Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                tint = neu.accent,
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        NetworkToolTab.entries.forEachIndexed { idx, tool ->
                            val toolTitle = tool.localizedTitle(s)
                            val toolSub = tool.localizedSubtitle(s)
                            NeumorphicCard(
                                onClick = {
                                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                    onOpenTools(tool.id)
                                },
                                cornerRadius = 20.dp,
                                modifier = Modifier
                                    .width(206.dp)
                                    .heightIn(min = 138.dp)
                                    .staggeredEntrance(idx + 2, delayPerItemMs = 50)
                                    .neumorphicTouchBounce(),
                            ) {
                                Column(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .background(neu.accent.copy(alpha = 0.15f)),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Icon(
                                            imageVector = tool.icon,
                                            contentDescription = toolTitle,
                                            tint = neu.accent,
                                            modifier = Modifier.size(20.dp),
                                        )
                                    }
                                    Spacer(Modifier.height(12.dp))
                                    Text(
                                        text = toolTitle,
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            fontFamily = fonts.headingFont,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                        color = neu.textPrimary,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = toolSub,
                                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = fonts.bodyFont),
                                        color = neu.textSecondary,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))

                // 7. SERVER LOCATION CARD
                NeumorphicCard(
                    onClick = onOpenServers,
                    modifier = Modifier
                        .fillMaxWidth()
                        .staggeredEntrance(3)
                        .neumorphicTouchBounce(),
                    cornerRadius = 22.dp,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val flag = selectedProxy?.emojiFlag
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(neu.surfaceElevated),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (!flag.isNullOrBlank()) {
                                Text(flag, fontSize = 24.sp)
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.Public,
                                    contentDescription = null,
                                    tint = neu.accent,
                                    modifier = Modifier.size(24.dp),
                                )
                            }
                        }

                        Spacer(Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = s.serverLocationLabel,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = fonts.headingFont,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = if (s.isRtl) 0.sp else 0.9.sp,
                                ),
                                color = neu.textSecondary,
                                maxLines = 1,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = selectedProxy?.let {
                                    s.language.localizeCountryName(it.countryName, it.countryCode)
                                } ?: s.recommendedFastest,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontFamily = fonts.headingFont,
                                    fontWeight = FontWeight.SemiBold,
                                ),
                                color = neu.textPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = s.islandChangeServer,
                            tint = neu.textSecondary,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }

                Spacer(Modifier.height(18.dp))

                // 7B. ANIMATED WORLD MAP ORIGIN-TO-EXIT ROUTE CARD
                WorldRouteMapCard(
                    state = state,
                    realIp = realIp,
                    cachedRealIp = app.settingsStore.cachedRealIp,
                    cachedRealCountry = app.settingsStore.cachedRealCountry,
                    selectedProxy = selectedProxy,
                    vpnEdgeIp = vpnEdgeIp,
                    protocolLabel = app.settingsStore.connectionProtocolMode.shortBadge,
                    adBlockEnabled = app.settingsStore.adBlockEnabled,
                    onOpenServers = onOpenServers,
                    modifier = Modifier
                        .fillMaxWidth()
                        .staggeredEntrance(3),
                )

                Spacer(Modifier.height(18.dp))

                // 8. CURRENT IP CARD:
                // - "CURRENT IP • TAP FOR LEAK TEST" on a single horizontal line
                // - Full IPv6/IPv4 address on a separate line inside a Quote Block / Framed Container
                NeumorphicCard(
                    onClick = { onOpenTools(NetworkToolTab.LEAK_INSPECTOR.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .staggeredEntrance(4)
                        .neumorphicTouchBounce(),
                    cornerRadius = 22.dp,
                ) {
                    val isConnected = state == ConnectionState.CONNECTED
                    val rawIpDisplay = if (isConnected) {
                        vpnEdgeIp?.let {
                            if (it.countryCode.isNotEmpty()) "${it.ip} • ${it.countryCode}" else it.ip
                        } ?: s.verifying
                    } else {
                        realIp?.let {
                            if (it.countryCode.isNotEmpty()) "${it.ip} • ${it.countryCode}" else it.ip
                        } ?: app.settingsStore.cachedRealIp?.let { cachedIp ->
                            val cachedCountry = app.settingsStore.cachedRealCountry
                            if (!cachedCountry.isNullOrEmpty()) "$cachedIp • $cachedCountry" else cachedIp
                        } ?: s.checking
                    }
                    val ipDisplay = s.language.localizeDigits(rawIpDisplay)
                    val badgeColor = if (isConnected) statusColors.connected else MaterialTheme.colorScheme.error

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 18.dp),
                    ) {
                        // Top Row: Shield Icon + Single-line "CURRENT IP • TAP FOR LEAK TEST" + Status Badge
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(if (isConnected) statusColors.connected.copy(alpha = 0.15f) else Color.Red.copy(alpha = 0.12f)),
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    imageVector = if (isConnected) Icons.Filled.Security else Icons.Filled.Shield,
                                    contentDescription = null,
                                    tint = badgeColor,
                                    modifier = Modifier.size(20.dp),
                                )
                            }

                            Spacer(Modifier.width(12.dp))

                            Text(
                                text = s.currentIpTapLeakTest,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = fonts.headingFont,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = if (s.isRtl) 0.sp else 0.5.sp,
                                ),
                                color = neu.textSecondary,
                                maxLines = 1,
                                softWrap = false,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f),
                            )

                            Spacer(Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(badgeColor.copy(alpha = 0.14f))
                                    .padding(horizontal = 10.dp, vertical = 5.dp),
                            ) {
                                Text(
                                    text = if (isConnected) s.badgeProtected else s.badgeExposed,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontFamily = fonts.headingFont,
                                        fontWeight = FontWeight.ExtraBold,
                                    ),
                                    color = badgeColor,
                                    maxLines = 1,
                                    softWrap = false,
                                )
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        // Separate Row Below: Styled Quote Block / Framed Box for IPv6 / IPv4 Address
                        val quoteShape = RoundedCornerShape(14.dp)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .neumorphicSunken(
                                    shape = quoteShape,
                                    cornerRadius = 14.dp,
                                    depth = 2.5.dp,
                                    surfaceColor = neu.surfaceElevated,
                                )
                                .border(
                                    width = 1.dp,
                                    color = badgeColor.copy(alpha = 0.24f),
                                    shape = quoteShape,
                                )
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // Quote Block Accent Bar
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height(22.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(badgeColor),
                            )
                            Spacer(Modifier.width(12.dp))
                            AnimatedContent(
                                targetState = ipDisplay,
                                transitionSpec = { fadeIn(tween(220)) togetherWith fadeOut(tween(160)) },
                                label = "ip-quote-box-anim",
                                modifier = Modifier.weight(1f),
                            ) { targetIp ->
                                Text(
                                    text = targetIp,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = fonts.monoFont,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    color = neu.textPrimary,
                                    maxLines = 1,
                                    softWrap = false,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))

                // 9. TELEMETRY, LIVE WAVEFORM SPARKLINE & QUOTA CARD
                NeumorphicCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .staggeredEntrance(5),
                    cornerRadius = 22.dp,
                ) {
                    Column(Modifier.padding(20.dp)) {
                        val isConnected = state == ConnectionState.CONNECTED
                        val remaining = quotaRemaining
                        val max = quotaMax

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // Data Used Sunken Box
                            val tileShape = RoundedCornerShape(14.dp)
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .neumorphicSunken(
                                        shape = tileShape,
                                        cornerRadius = 14.dp,
                                        depth = 2.5.dp,
                                        surfaceColor = neu.surfaceElevated,
                                    )
                                    .padding(horizontal = 16.dp, vertical = 14.dp),
                            ) {
                                Column {
                                    Text(
                                        text = s.dataUsedLabel,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontFamily = fonts.headingFont,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = if (s.isRtl) 0.sp else 0.8.sp,
                                        ),
                                        color = neu.textSecondary,
                                        maxLines = 1,
                                    )
                                    Spacer(Modifier.height(6.dp))
                                    Text(
                                        text = if (isConnected) {
                                            s.language.formatBytesLocalized(liveStats.sessionBytes)
                                        } else {
                                            s.language.formatBytesLocalized(0L)
                                        },
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontFamily = fonts.monoFont,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                        color = neu.textPrimary,
                                        maxLines = 1,
                                    )
                                }
                            }

                            // Latency / Ping Sunken Box
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .neumorphicSunken(
                                        shape = tileShape,
                                        cornerRadius = 14.dp,
                                        depth = 2.5.dp,
                                        surfaceColor = neu.surfaceElevated,
                                    )
                                    .clickable(role = Role.Button) {
                                        if (!isPinging) {
                                            scope.launch {
                                                isPinging = true
                                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                                if (state == ConnectionState.CONNECTED) {
                                                    FoxyVpnService.pingNow()
                                                } else {
                                                    val target = selectedProxy ?: app.proxyStateStore.load() ?: run {
                                                        val cached = app.serverListClient.loadCachedCountries()
                                                        ServerListClient.candidatesForCountry(cached, RECOMMENDED_COUNTRY_CODE).firstOrNull()
                                                            ?: cached.firstOrNull { it.code.isNotBlank() }?.let { country ->
                                                                ServerListClient.candidatesForCountry(cached, country.code).firstOrNull()
                                                            }
                                                    }
                                                    if (target != null) {
                                                        disconnectedPingLatency = PingUtil.measureTcpLatencyMs(target.host, target.port)
                                                    }
                                                }
                                                isPinging = false
                                            }
                                        }
                                    }
                                    .padding(horizontal = 16.dp, vertical = 14.dp),
                            ) {
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = s.latencyLabel,
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontFamily = fonts.headingFont,
                                                fontWeight = FontWeight.Bold,
                                                letterSpacing = if (s.isRtl) 0.sp else 0.8.sp,
                                            ),
                                            color = neu.textSecondary,
                                            maxLines = 1,
                                        )
                                        Spacer(Modifier.width(6.dp))
                                        Icon(
                                            imageVector = Icons.Filled.NetworkCheck,
                                            contentDescription = s.latencyLabel,
                                            tint = neu.textSecondary,
                                            modifier = Modifier.size(15.dp),
                                        )
                                    }
                                    Spacer(Modifier.height(6.dp))
                                    val currentPing = if (isConnected) pingLatency else disconnectedPingLatency
                                    Text(
                                        text = when {
                                            isPinging -> s.testing
                                            currentPing != null -> s.language.formatPingLocalized(currentPing)
                                            else -> "\u2014"
                                        },
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontFamily = fonts.monoFont,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                        color = if (currentPing != null) statusColors.connected else neu.textPrimary,
                                        maxLines = 1,
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        // Live Traffic Waveform Sparkline Graph (Header separated from Canvas to prevent overlap)
                        val waveShape = RoundedCornerShape(14.dp)
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .neumorphicSunken(
                                    shape = waveShape,
                                    cornerRadius = 14.dp,
                                    depth = 2.5.dp,
                                    surfaceColor = neu.surfaceElevated,
                                )
                                .padding(horizontal = 14.dp, vertical = 10.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "↓ ${s.language.formatSpeedLocalized(liveStats.rxRateBytesPerSec)}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontFamily = fonts.monoFont,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    color = if (isConnected) statusColors.connected else neu.textSecondary,
                                    maxLines = 1,
                                )
                                Text(
                                    text = "↑ ${s.language.formatSpeedLocalized(liveStats.txRateBytesPerSec)}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontFamily = fonts.monoFont,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    color = statusColors.connecting,
                                    maxLines = 1,
                                )
                            }
                            Spacer(Modifier.height(6.dp))
                            LiveTrafficSparkline(
                                rxHistory = rxHistory,
                                txHistory = txHistory,
                                rxColor = if (isConnected) statusColors.connected else neu.accent,
                                txColor = statusColors.connecting,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp),
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        // Quota Details & Sunken Progress Bar
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = s.monthlyQuotaLabel,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontFamily = fonts.bodyFont,
                                    fontWeight = FontWeight.SemiBold,
                                ),
                                color = neu.textSecondary,
                                modifier = Modifier.weight(1f),
                            )
                            val quotaText = when {
                                isUnlimited -> s.unlimitedQuota
                                remaining != null && max != null ->
                                    "${s.language.formatBytesLocalized(remaining)} / ${s.language.formatBytesLocalized(max)}"
                                remaining != null -> s.language.formatBytesLocalized(remaining)
                                max != null -> "${s.quotaLimitPrefix} ${s.language.formatBytesLocalized(max)}"
                                else -> "\u2014"
                            }
                            Text(
                                text = quotaText,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontFamily = fonts.monoFont,
                                    fontWeight = FontWeight.Bold,
                                ),
                                color = neu.textPrimary,
                            )
                        }

                        Spacer(Modifier.height(10.dp))

                        val rawQuotaFraction = when {
                            isUnlimited -> 1f
                            max != null && max > 0L && remaining != null ->
                                (remaining.toFloat() / max.toFloat()).coerceIn(0f, 1f)
                            else -> 0f
                        }
                        val animatedQuotaFraction by animateFloatAsState(
                            targetValue = rawQuotaFraction,
                            animationSpec = tween(600, easing = FastOutSlowInEasing),
                            label = "quota-bar-fill",
                        )

                        val progressShape = RoundedCornerShape(7.dp)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(12.dp)
                                .neumorphicSunken(
                                    shape = progressShape,
                                    cornerRadius = 7.dp,
                                    depth = 2.dp,
                                    surfaceColor = neu.surfaceElevated,
                                ),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(animatedQuotaFraction.coerceIn(0f, 1f))
                                    .fillMaxHeight()
                                    .clip(progressShape)
                                    .background(
                                        if (isConnected) statusColors.connected
                                        else neu.accent.copy(alpha = 0.8f),
                                    ),
                            )
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))

                // 10. SPEED BOOSTER, BATTERY SAVER & SMART AUTOMATION CARD
                val configRev by app.settingsStore.islandConfigRevision.collectAsState()
                @Suppress("UNUSED_EXPRESSION")
                configRev
                var unmeteredBoost by remember(configRev) { mutableStateOf(app.settingsStore.unmeteredVpnModeEnabled) }
                var batteryEco by remember(configRev) { mutableStateOf(app.settingsStore.batterySaverModeEnabled) }
                var scheduleEnabled by remember(configRev) { mutableStateOf(app.settingsStore.scheduleEnabled) }
                var idleEnabled by remember(configRev) { mutableStateOf(app.settingsStore.idleDisconnectEnabled) }
                val idleSeconds by FoxyVpnService.idleTimerSeconds.collectAsState()

                NeumorphicCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .staggeredEntrance(6),
                    cornerRadius = 22.dp,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 18.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = s.sectionSmartAutomation,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = fonts.headingFont,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = if (s.isRtl) 0.sp else 0.8.sp,
                                ),
                                color = neu.textSecondary,
                                modifier = Modifier.weight(1f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = s.headerSettingsButton,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = fonts.headingFont,
                                    fontWeight = FontWeight.Bold,
                                ),
                                color = neu.accent,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { onOpenSettings() }
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                            )
                        }

                        Spacer(Modifier.height(10.dp))

                        // Row 0a: Unmetered High-Speed Tunnel Mode + 256KB/1MB Turbo Buffer
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = s.unmeteredSpeedBoostTitle,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontFamily = fonts.headingFont,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    color = neu.textPrimary,
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = s.unmeteredSpeedBoostSub,
                                    style = MaterialTheme.typography.bodySmall.copy(fontFamily = fonts.bodyFont),
                                    color = if (unmeteredBoost) statusColors.connected else neu.textSecondary,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            Spacer(Modifier.width(8.dp))
                            Switch(
                                checked = unmeteredBoost,
                                onCheckedChange = {
                                    unmeteredBoost = it
                                    app.settingsStore.unmeteredVpnModeEnabled = it
                                    app.settingsStore.tcpBufferBoostEnabled = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = statusColors.connected,
                                ),
                            )
                        }

                        HorizontalDivider(
                            color = neu.textSecondary.copy(alpha = 0.12f),
                            modifier = Modifier.padding(vertical = 10.dp),
                        )

                        // Row 0b: Smart Battery Saver (Eco Mode)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = s.batterySaverModeTitle,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontFamily = fonts.headingFont,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    color = neu.textPrimary,
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = s.batterySaverModeSub,
                                    style = MaterialTheme.typography.bodySmall.copy(fontFamily = fonts.bodyFont),
                                    color = if (batteryEco) neu.accent else neu.textSecondary,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            Spacer(Modifier.width(8.dp))
                            Switch(
                                checked = batteryEco,
                                onCheckedChange = {
                                    batteryEco = it
                                    app.settingsStore.batterySaverModeEnabled = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = neu.accent,
                                ),
                            )
                        }

                        HorizontalDivider(
                            color = neu.textSecondary.copy(alpha = 0.12f),
                            modifier = Modifier.padding(vertical = 10.dp),
                        )

                        // Row 1: Scheduled Auto-Connect / Auto-Disconnect
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = s.scheduleVpnTitle,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontFamily = fonts.headingFont,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    color = neu.textPrimary,
                                )
                                Spacer(Modifier.height(2.dp))
                                val startFmt = s.language.formatClockTimeLocalized(
                                    app.settingsStore.scheduleStartHour,
                                    app.settingsStore.scheduleStartMinute,
                                )
                                val endFmt = s.language.formatClockTimeLocalized(
                                    app.settingsStore.scheduleEndHour,
                                    app.settingsStore.scheduleEndMinute,
                                )
                                Text(
                                    text = "${s.scheduleFromLabel}: $startFmt  •  ${s.scheduleToLabel}: $endFmt",
                                    style = MaterialTheme.typography.bodySmall.copy(fontFamily = fonts.monoFont),
                                    color = if (scheduleEnabled) neu.accent else neu.textSecondary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            Switch(
                                checked = scheduleEnabled,
                                onCheckedChange = {
                                    scheduleEnabled = it
                                    app.settingsStore.scheduleEnabled = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = neu.accent,
                                ),
                            )
                        }

                        HorizontalDivider(
                            color = neu.textSecondary.copy(alpha = 0.12f),
                            modifier = Modifier.padding(vertical = 10.dp),
                        )

                        // Row 2: Smart Idle Auto-Disconnect (Battery & Data Saver)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = s.idleDisconnectTitle,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontFamily = fonts.headingFont,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    color = neu.textPrimary,
                                )
                                Spacer(Modifier.height(2.dp))
                                val timeoutFmt = s.language.formatMinutesLocalized(app.settingsStore.idleTimeoutMinutes)
                                val idleStatus = if (state == ConnectionState.CONNECTED && idleEnabled) {
                                    val remSec = (app.settingsStore.idleTimeoutMinutes * 60L - idleSeconds).coerceAtLeast(0L)
                                    "$timeoutFmt (${s.language.formatDurationLocalized(remSec, alwaysShowHours = false)})"
                                } else {
                                    "${s.idleTimeoutTitle}: $timeoutFmt"
                                }
                                Text(
                                    text = idleStatus,
                                    style = MaterialTheme.typography.bodySmall.copy(fontFamily = fonts.monoFont),
                                    color = if (idleEnabled) statusColors.connected else neu.textSecondary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            Switch(
                                checked = idleEnabled,
                                onCheckedChange = {
                                    idleEnabled = it
                                    app.settingsStore.idleDisconnectEnabled = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = statusColors.connected,
                                ),
                            )
                        }
                    }
                }

                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

/**
 * Interactive Animated World Map Route Card displaying Origin Country & Real IP
 * connected via an animated glowing Bezier tunnel arc to the Exit Server Country & Tunnel IP.
 */
@Composable
private fun WorldRouteMapCard(
    state: ConnectionState,
    realIp: IpLocationInfo?,
    cachedRealIp: String?,
    cachedRealCountry: String?,
    selectedProxy: ProxyCandidate?,
    vpnEdgeIp: IpLocationInfo?,
    protocolLabel: String,
    adBlockEnabled: Boolean,
    onOpenServers: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val neu = currentNeumorphicColors
    val statusColors = LocalFoxyStatusColors.current
    val s = currentFoxyStrings
    val fonts = currentFoxyFonts
    val isConnected = state == ConnectionState.CONNECTED
    val isConnecting = state == ConnectionState.CONNECTING

    val originCountryCode = (realIp?.countryCode?.takeIf { it.isNotBlank() }
        ?: cachedRealCountry?.takeIf { it.isNotBlank() }
        ?: "IR").uppercase()
    val originIpText = s.language.localizeDigits(
        realIp?.ip?.takeIf { it.isNotBlank() }
            ?: cachedRealIp?.takeIf { it.isNotBlank() }
            ?: s.checking
    )

    val exitCountryCode = (selectedProxy?.countryCode?.takeIf { it.isNotBlank() }
        ?: vpnEdgeIp?.countryCode?.takeIf { it.isNotBlank() }
        ?: "DE").uppercase()
    val exitCountryName = selectedProxy?.let {
        s.language.localizeCountryName(it.countryName, it.countryCode)
    } ?: s.language.localizeCountryName(exitCountryCode, exitCountryCode)
    val exitIpText = s.language.localizeDigits(
        when {
            isConnected && vpnEdgeIp != null -> vpnEdgeIp.ip
            selectedProxy != null -> "${selectedProxy.host}:${selectedProxy.port}"
            else -> s.recommendedFastest
        }
    )

    val infiniteTransition = rememberInfiniteTransition(label = "world-route-arc")
    val packetProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = if (isConnected) 2200 else 3400,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "route-packet-progress",
    )
    val pulseRadius by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "route-node-pulse",
    )

    val routeColor = when (state) {
        ConnectionState.CONNECTED -> statusColors.connected
        ConnectionState.CONNECTING -> statusColors.connecting
        ConnectionState.DISCONNECTED -> neu.accent
    }

    NeumorphicCard(
        onClick = onOpenServers,
        cornerRadius = 22.dp,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp),
        ) {
            // Card Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = when (s.language) {
                        FoxyLanguage.FA -> "نقشه مسیر رمزنگاری و تونل جهانی"
                        FoxyLanguage.AR -> "خريطة مسار النفق العالمي المشفر"
                        FoxyLanguage.EN -> "GLOBAL ENCRYPTED TUNNEL ROUTE MAP"
                    },
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = fonts.headingFont,
                        fontWeight = FontWeight.Bold,
                    ),
                    color = neu.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (adBlockEnabled) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(statusColors.connected.copy(alpha = 0.14f))
                                .padding(horizontal = 8.dp, vertical = 3.dp),
                        ) {
                            Text(
                                text = if (s.language == FoxyLanguage.FA) "ضد تبلیغ فعال" else "AdBlock",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = fonts.headingFont,
                                    fontWeight = FontWeight.Bold,
                                ),
                                color = statusColors.connected,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(routeColor.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 3.dp),
                    ) {
                        Text(
                            text = protocolLabel,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = fonts.monoFont,
                                fontWeight = FontWeight.ExtraBold,
                            ),
                            color = routeColor,
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // Stylized World Map Canvas with Origin -> Exit Animated Bezier Arc
            val mapShape = RoundedCornerShape(16.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(132.dp)
                    .neumorphicSunken(
                        shape = mapShape,
                        cornerRadius = 16.dp,
                        depth = 2.5.dp,
                        surfaceColor = neu.surfaceElevated,
                    )
                    .clip(mapShape),
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height

                    // Subtle Latitude & Longitude Grid
                    val gridColor = neu.textSecondary.copy(alpha = 0.10f)
                    for (i in 1..4) {
                        val y = h * (i / 5f)
                        drawLine(gridColor, Offset(0f, y), Offset(w, y), strokeWidth = 1f)
                    }
                    for (i in 1..7) {
                        val x = w * (i / 8f)
                        drawLine(gridColor, Offset(x, 0f), Offset(x, h), strokeWidth = 1f)
                    }

                    // Stylized World Landmass Dot Matrix (North America, Europe, Middle East, Asia, etc.)
                    val landDotColor = neu.textSecondary.copy(alpha = 0.18f)
                    val landClusters = listOf(
                        // North America
                        Offset(0.18f, 0.34f), Offset(0.22f, 0.38f), Offset(0.26f, 0.42f), Offset(0.15f, 0.40f),
                        // South America
                        Offset(0.29f, 0.66f), Offset(0.32f, 0.74f), Offset(0.30f, 0.82f),
                        // Europe
                        Offset(0.47f, 0.28f), Offset(0.50f, 0.31f), Offset(0.53f, 0.29f), Offset(0.49f, 0.35f), Offset(0.52f, 0.34f),
                        // Middle East & Africa
                        Offset(0.58f, 0.43f), Offset(0.61f, 0.45f), Offset(0.52f, 0.54f), Offset(0.55f, 0.65f),
                        // Asia & Oceania
                        Offset(0.68f, 0.36f), Offset(0.74f, 0.40f), Offset(0.79f, 0.46f), Offset(0.83f, 0.38f), Offset(0.82f, 0.74f),
                    )
                    landClusters.forEach { pt ->
                        drawCircle(
                            color = landDotColor,
                            radius = 11.dp.toPx(),
                            center = Offset(pt.x * w, pt.y * h),
                        )
                    }

                    val originNorm = countryCodeToMapNorm(originCountryCode)
                    val exitNorm = countryCodeToMapNorm(exitCountryCode)
                    val startPt = Offset(originNorm.x * w, originNorm.y * h)
                    val endPt = Offset(exitNorm.x * w, exitNorm.y * h)

                    val midX = (startPt.x + endPt.x) * 0.5f
                    val arcLift = (kotlin.math.abs(endPt.x - startPt.x) * 0.34f).coerceIn(26.dp.toPx(), 64.dp.toPx())
                    val controlPt = Offset(midX, (minOf(startPt.y, endPt.y) - arcLift).coerceAtLeast(12.dp.toPx()))

                    val arcPath = Path().apply {
                        moveTo(startPt.x, startPt.y)
                        quadraticTo(controlPt.x, controlPt.y, endPt.x, endPt.y)
                    }

                    // Outer Glow of Route Arc
                    drawPath(
                        path = arcPath,
                        color = routeColor.copy(alpha = if (isConnected) 0.26f else 0.14f),
                        style = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round),
                    )

                    // Dashed or Solid Main Route Arc
                    drawPath(
                        path = arcPath,
                        color = routeColor.copy(alpha = if (isConnected) 0.90f else 0.55f),
                        style = Stroke(
                            width = 2.2.dp.toPx(),
                            cap = StrokeCap.Round,
                            pathEffect = if (isConnected) null else PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f),
                        ),
                    )

                    // Animated Data Packet traveling from Origin to Exit along Quadratic Bezier
                    if (isConnected || isConnecting) {
                        val t = packetProgress
                        val invT = 1f - t
                        val pktX = invT * invT * startPt.x + 2f * invT * t * controlPt.x + t * t * endPt.x
                        val pktY = invT * invT * startPt.y + 2f * invT * t * controlPt.y + t * t * endPt.y
                        drawCircle(
                            color = routeColor.copy(alpha = 0.35f),
                            radius = 8.dp.toPx(),
                            center = Offset(pktX, pktY),
                        )
                        drawCircle(
                            color = Color.White,
                            radius = 3.6.dp.toPx(),
                            center = Offset(pktX, pktY),
                        )
                    }

                    // Origin Node
                    drawCircle(
                        color = neu.textSecondary.copy(alpha = 0.28f * pulseRadius),
                        radius = 10.dp.toPx() * pulseRadius,
                        center = startPt,
                    )
                    drawCircle(
                        color = neu.textPrimary,
                        radius = 4.5.dp.toPx(),
                        center = startPt,
                    )

                    // Exit Server Node
                    drawCircle(
                        color = routeColor.copy(alpha = 0.32f * pulseRadius),
                        radius = 12.dp.toPx() * pulseRadius,
                        center = endPt,
                    )
                    drawCircle(
                        color = routeColor,
                        radius = 5.5.dp.toPx(),
                        center = endPt,
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Two-Column Origin vs Exit Server Summary
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Origin Column
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(neu.surfaceElevated.copy(alpha = 0.65f))
                        .padding(horizontal = 12.dp, vertical = 9.dp),
                ) {
                    val originCountryLocalized = s.language.localizeCountryName(originCountryCode, originCountryCode)
                    Text(
                        text = when (s.language) {
                            FoxyLanguage.FA -> "کشور مبدأ ($originCountryLocalized)"
                            FoxyLanguage.AR -> "بلد المصدر ($originCountryLocalized)"
                            FoxyLanguage.EN -> "ORIGIN ($originCountryLocalized)"
                        },
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = fonts.headingFont,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = neu.textSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = originIpText,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = fonts.monoFont,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = neu.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                // Exit Server Column
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(routeColor.copy(alpha = 0.10f))
                        .border(1.dp, routeColor.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 9.dp),
                ) {
                    Text(
                        text = when (s.language) {
                            FoxyLanguage.FA -> "سرور خروجی ($exitCountryName)"
                            FoxyLanguage.AR -> "خادم الخروج ($exitCountryName)"
                            FoxyLanguage.EN -> "EXIT ($exitCountryName)"
                        },
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = fonts.headingFont,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = routeColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = exitIpText,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = fonts.monoFont,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = neu.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

private fun countryCodeToMapNorm(code: String): Offset = when (code.uppercase()) {
    "US" -> Offset(0.21f, 0.39f)
    "CA" -> Offset(0.20f, 0.28f)
    "BR" -> Offset(0.32f, 0.72f)
    "GB", "UK" -> Offset(0.46f, 0.29f)
    "FR" -> Offset(0.48f, 0.34f)
    "NL" -> Offset(0.49f, 0.30f)
    "DE" -> Offset(0.51f, 0.31f)
    "CH" -> Offset(0.50f, 0.35f)
    "AT" -> Offset(0.52f, 0.35f)
    "IT" -> Offset(0.51f, 0.39f)
    "ES" -> Offset(0.45f, 0.39f)
    "SE", "NO" -> Offset(0.52f, 0.22f)
    "FI" -> Offset(0.55f, 0.21f)
    "PL", "CZ", "RO" -> Offset(0.54f, 0.32f)
    "TR" -> Offset(0.57f, 0.39f)
    "IR" -> Offset(0.62f, 0.43f)
    "AE" -> Offset(0.62f, 0.49f)
    "SG" -> Offset(0.76f, 0.58f)
    "JP" -> Offset(0.85f, 0.39f)
    "HK" -> Offset(0.79f, 0.47f)
    "AU" -> Offset(0.83f, 0.76f)
    else -> Offset(0.51f, 0.32f)
}

